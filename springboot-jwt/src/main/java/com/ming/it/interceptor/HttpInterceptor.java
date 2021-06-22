package com.ming.it.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ming.it.entity.User;
import com.ming.it.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author 11119638
 * @date 2021/6/22 9:40
 */
@Component
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 在请求头中获取token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("无token，请重新登录");
        }
        // 刚刚我们获取token的时候,token保存的是userID,这里就是用来获取用户ID的
        final User user = userService.queryUserById(JWT.decode(token).getAudience().get(0));
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户未登录");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("非法token");
        }
    }
}
