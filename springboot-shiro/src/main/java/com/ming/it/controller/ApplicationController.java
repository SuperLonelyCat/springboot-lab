package com.ming.it.controller;

import com.ming.it.constant.ShiroConstant;
import com.ming.it.entity.Account;
import com.ming.it.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 11119638
 * @date 2021/6/28 14:25
 */
@RestController
@Validated
public class ApplicationController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/noRole")
    public String noRole() {
        return "对不起，您没有角色权限！";
    }

    @GetMapping("/user")
    public String getUser() {
        return "用户可以访问！";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "管理员可以访问！";
    }

    @PostMapping("/registry")
    // 接受JSON数据
    public boolean registry(@RequestBody @Validated Account account) {
        account.setPassword(new SimpleHash(ShiroConstant.MD5, account.getPassword(), account.getCode(),
                ShiroConstant.ASHITERATIONS).toHex());
        return accountService.createAccount(account);
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})  // 不写请求方式，POST和GET请求都可请求
    // 接受表单数据: form-data
    public String login(String username, String password) {
        // 判断重定向
        if(StringUtils.isBlank(username)) {
            return "请登录！";
        }
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 根据异常判断登录信息
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            return "当前用户没有权限！";
        } catch (Exception e) {
            return "未知异常！";
        }
        return "登录成功！";
    }
}
