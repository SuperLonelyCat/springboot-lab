package com.ming.it.config;

import com.ming.it.filter.BaseFilter;
import com.ming.it.filter.WebCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author 11119638
 * @date 2021/6/17 15:48
 */
// 注册过滤器
@Configuration
public class FilterConfig {

    @Autowired
    private BaseFilter baseFilter;

    @Bean
    public FilterRegistrationBean<Filter> baseFilterRegistration() {

        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(baseFilter);
        registration.addUrlPatterns("/*");
        // 设置执行顺序
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<Filter> webCheckFilterRegistration() {

        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(webCheckFilter());
        registration.addUrlPatterns("/*");
        // 设置执行顺序，数值越小，优先级越高
        registration.setOrder(1);
        return registration;
    }

//    @Bean
//    public Filter baseFilter() {
//        return new BaseFilter();
//    }

    @Bean
    public Filter webCheckFilter() {
        return new WebCheckFilter();
    }
}
