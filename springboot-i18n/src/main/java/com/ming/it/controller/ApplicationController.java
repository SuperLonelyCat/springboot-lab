package com.ming.it.controller;

import com.ming.it.common.BusinessException;
import com.ming.it.common.Result;
import com.ming.it.common.ResultCode;
import com.ming.it.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author 11119638
 * @date 2021/6/23 10:00
 */
@RestController
public class ApplicationController {

    // SpringBoot通过MessageSourceAutoConfiguration自动配置MessageSource实例
    @Autowired
    MessageSource messageSource;

    @GetMapping("/get")
    public String getMsg() {
        // 500测试
        // int temp = 1 / 0;
        return messageSource.getMessage("name", null, LocaleContextHolder.getLocale());
    }

    @GetMapping("/get/msg/result")
    public Result<String> getResult() {
        return Result.success(messageSource.getMessage("user.name", null, LocaleContextHolder.getLocale()));
    }

    @GetMapping("/get/name")
    public String getName(@RequestParam String name) {
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(ResultCode.OBJECT_NOT_EXIST);
        }
        return name;
    }

    @PostMapping("/get/user")
    public UserVO getUser(@RequestBody UserVO userVO) {
        if (Objects.isNull(userVO)) {
            throw new BusinessException(ResultCode.OBJECT_NOT_EXIST);
        }
        return userVO;
    }
}
