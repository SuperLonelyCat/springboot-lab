package com.ming.it.config;

import com.ming.it.interceptor.BaseInterceptor;
import com.ming.it.interceptor.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 11119638
 * @date 2021/6/19 14:44
 */
// 注册拦截器
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 默认先注册先执行
        // 通过order指定执行顺序，值越小，优先级越高
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").order(2);
        registry.addInterceptor(baseInterceptor).addPathPatterns("/**").order(1);
    }
}
