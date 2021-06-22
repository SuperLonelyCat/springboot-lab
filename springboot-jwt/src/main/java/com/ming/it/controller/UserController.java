package com.ming.it.controller;

import com.ming.it.service.UserService;
import com.ming.it.util.JWTUtil;
import com.ming.it.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author 11119638
 * @date 2021/6/22 9:13
 */
@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping不能和@RequestBody一起使用
    @PostMapping ("/login")
    public String login(@RequestBody @Validated UserVO userVO) {
        if (Objects.isNull(userService.queryUser(userVO))) {
            throw new RuntimeException("用户未注册");
        }
        return JWTUtil.getToken(userVO.getUid(), userVO.getPassword());
    }

    @GetMapping("/get")
    public String getInfo(@RequestParam String uid) {
        return userService.queryUserById(uid).toString();
    }
}
