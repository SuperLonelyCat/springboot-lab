package com.ming.it.common;

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
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable e, HttpServletRequest request) {
        return "url - " + request.getRequestURI() + " , exception - " + e.getMessage();
    }
}
