package com.ming.it.config;

import com.ming.it.constant.ShiroConstant;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 11119638
 * @date 2021/6/28 16:35
 */
@Configuration
public class ShiroConfig {

    // 自定义加密算法(同注册密码加密算法保持一致)
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        // Shiro自带加密算法
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 使用md5散列算法
        credentialsMatcher.setHashAlgorithmName(ShiroConstant.MD5);
        // 散列次数，md5(md5(md5()))
        credentialsMatcher.setHashIterations(ShiroConstant.ASHITERATIONS);
        // 以十六进制形式输出
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    // 注入算法
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    // 定义安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    // Filter工厂，设置对应的过滤条件和跳转条件
    /*
     * Shiro内置过滤器
     * anon         org.apache.shiro.web.filter.authc.AnonymousFilter
     * authc        org.apache.shiro.web.filter.authc.FormAuthenticationFilter
     * authcBasic   org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
     * perms        org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilte
     * port         org.apache.shiro.web.filter.authz.PortFilter
     * rest         org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
     * roles        org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
     * ssl          org.apache.shiro.web.filter.authz.SslFilter
     * user         org.apache.shiro.web.filter.authc.UserFilter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 重定向Get请求到登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 重定向Get请求到未授权
        shiroFilterFactoryBean.setUnauthorizedUrl("/noRole");

        // 设置地址请求权限
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        // Get请求
        filterChainDefinitionMap.put("/user", "roles[user]");
        // Get请求
        filterChainDefinitionMap.put("/admin", "roles[admin]");
        // 开放登录接口权限(如果没有开放，访问 /login 成功后自动跳转到上一次访问的接口 /user)
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
