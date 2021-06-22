package com.ming.it.config;

import com.ming.it.interceptor.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 11119638
 * @date 2021/6/22 9:46
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
