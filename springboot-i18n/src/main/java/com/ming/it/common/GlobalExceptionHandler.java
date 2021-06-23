package com.ming.it.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 11119638
 * @date 2021/6/22 10:06
 */
// 需要加上@RestController + @ControllerAdvice 或 @RestControllerAdvice，否则出现404
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result<?> handleThrowable(Throwable e, HttpServletRequest request) {
        Result<?> error = Result.fail(ResultCode.EXCEPTION);
        log.warn("url - {}, exception - {}", request.getRequestURI(), ExceptionUtils.getStackTrace(e));
        return error;
    }

    @ExceptionHandler(BusinessException.class)
    // T没有被使用，data类型未知，用?代替
    public Result<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        Result<?> error = Result.fail(e.getCode(), e.getMessage());
        log.warn("url - {}, exception - {}", request.getRequestURI(), ExceptionUtils.getStackTrace(e));
        return error;
    }
}
