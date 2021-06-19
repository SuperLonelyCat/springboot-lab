package com.ming.it.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 11119638
 * @date 2021/6/19 14:26
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("拦截器前置处理 - HttpInterceptor");
        request.setAttribute(START_TIME, System.currentTimeMillis());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.warn("拦截器后置处理 - HttpInterceptor");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.warn("拦截器完成后处理 - HttpInterceptor");
        log.warn("HttpInterceptor - 请求耗费时间：{} ms", System.currentTimeMillis() - (long) request.getAttribute(START_TIME));
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
