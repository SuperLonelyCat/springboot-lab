package com.ming.it.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 11119638
 * @date 2021/6/17 14:25
 */
@WebFilter
@Slf4j
public class WebCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.warn("初始化过滤器 - WebCheckFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.warn("执行过滤器前 - WebCheckFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        log.warn("执行过滤器后 - WebCheckFilter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.warn("销毁过滤器 - WebCheckFilter");
    }
}
