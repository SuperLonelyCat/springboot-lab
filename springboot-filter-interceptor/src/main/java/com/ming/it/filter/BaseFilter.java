package com.ming.it.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 11119638
 * @date 2021/6/17 14:25
 */
/*
过滤器两种注册方式：
    1、@ServletComponentScan + @WebFilter 可通过类名进行排序（首字母顺序）
    2、@Configuration + @Bean 进行注册，可自定义排序
 */
// @WebFilter
@Component
@Slf4j
// 定义Spring IOC容器中Bean的执行顺序的优先级
// @Order(1)：在@WebFilter注解下不能生效，可通过设置类名指定拦截器执行顺序
public class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.warn("初始化过滤器 - BaseFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.warn("执行过滤器前 - BaseFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        log.warn("执行过滤器后 - BaseFilter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.warn("销毁过滤器 - BaseFilter");
    }
}
