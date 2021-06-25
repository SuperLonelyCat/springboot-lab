package com.ming.it.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 11119638
 * @date 2021/6/22 10:06
 */
// 需要加上@RestController + @ControllerAdvice 或 @RestControllerAdvice，否则返回404
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 拦截500异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result<?> handleThrowable(Throwable e, HttpServletRequest request) {
        Result<?> error = Result.fail(ResultCode.EXCEPTION);
        log.warn("url - {}, exception - {}", request.getRequestURI(), ExceptionUtils.getStackTrace(e));
        return error;
    }

    // 拦截自定义异常
    @ExceptionHandler(BusinessException.class)
    // T没有被使用，data类型未知，用?代替
    public Result<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        Result<?> error = Result.fail(e.getCode(), e.getMessage());
        log.warn("url - {}, exception - {}", request.getRequestURI(), ExceptionUtils.getStackTrace(e));
        return error;
    }

    // 拦截注解校验参数
    // 默认支持国际化
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleParameterException(MethodArgumentNotValidException e) {
        List<ObjectError> list = e.getBindingResult().getAllErrors();
        StringBuilder builder = new StringBuilder();
        for (ObjectError error : list) {
            if (error instanceof FieldError) {
                builder.append(((FieldError) error).getField()).append(StringUtils.SPACE).append(error.getDefaultMessage()).append("; ");
            } else {
                builder.append(error.getObjectName()).append(StringUtils.SPACE).append(error.getDefaultMessage()).append("; ");
            }
        }
        return Result.fail(ResultCode.PARAM_ERROR.getCode(), builder.substring(NumberUtils.INTEGER_ZERO, builder.length()
                - NumberUtils.INTEGER_TWO));
    }
}
