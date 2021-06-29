package com.ming.it.config;

import com.ming.it.entity.Account;
import com.ming.it.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 11119638
 * @date 2021/6/28 16:59
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        final String username = principalCollection.getPrimaryPrincipal().toString();
        log.warn("Authorization | username - {}", username);
        final SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 设置用户角色
        final Account account = accountService.queryByCode(username);
        if (Objects.isNull(account) || StringUtils.isBlank(account.getRoles())) {
            return null;
        }
        simpleAuthorizationInfo.setRoles(Arrays.stream(account.getRoles().split(",")).collect(Collectors.toSet()));
        // 设置用户权限
        return simpleAuthorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 获取UsernamePasswordToken(含有username、password、rememberMe以及host属性)中的username
        String code = authenticationToken.getPrincipal().toString();
        log.warn("Authentication | username - {}, password - {}", code, authenticationToken.getCredentials());
        Account account = accountService.queryByCode(code);
        return Objects.isNull(account) ? null : new SimpleAuthenticationInfo(code, account.getPassword(),
                ByteSource.Util.bytes(code), getName());
    }
}
