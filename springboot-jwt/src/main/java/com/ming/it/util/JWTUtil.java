package com.ming.it.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author 11119638
 * @date 2021/6/21 16:55
 */
public class JWTUtil {

    // 过期时间: s
    private static final long EXPIRE_DATE = 3600L;

    private JWTUtil() {
        // 构造器私有
    }

    public static String getToken(String userId, String password) {
        return JWT.create()
                .withAudience(userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_DATE * 1000))  // 设置Token过期时间
                .sign(Algorithm.HMAC256(password));
    }
}
