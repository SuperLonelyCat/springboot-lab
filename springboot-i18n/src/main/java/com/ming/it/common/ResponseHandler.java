package com.ming.it.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.it.constant.WebConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 11119638
 * @date 2020/12/7 15:05
 */
@ControllerAdvice
@Slf4j
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    @Autowired
    private MessageSource messageSource;

    /**
     * 定义要拦截的方法
     * @param methodParameter 待拦截的方法
     * @param aClass 指定所使用的转换器
     * @return true/false表示响应之前执行/不执行beforeBodyWrite方法
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends
            HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 方法返回数据为String类型，会报类型转换异常
        /*
            SpringMVC内部定义了九个不同的MessageConverter用来处理不同的返回值，每个MessageConverer是根据返回类型和媒体类型来
          选择处理的。
            当Controller返回String时，选择了StringMessageConverter来处理，此类型只接受String返回类型，由于在ResponseHandler
          中将String转化为Result类型，因此会将Result类型强制转为String，自然会发生异常。
        */
        String message = messageSource.getMessage(WebConstant.RESULT_CODE_PREFIX + ResultCode.SUCCESS.getCode(),
                null, LocaleContextHolder.getLocale());
        // 用于处理异常结果
        if (o instanceof Result) {
            message = messageSource.getMessage(WebConstant.RESULT_CODE_PREFIX + ((Result<?>) o).getCode(),
                    null, LocaleContextHolder.getLocale());
            ((Result<?>) o).setMessage(message);
            return o;
        }
        // 处理正常结果
        final Result<Object> success = Result.success(o);
        success.setMessage(message);
        // 返回数据为String类型
        if (o instanceof String) {
            final ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(success);
            } catch (JsonProcessingException e) {
                log.error("Failed to convert object to string");
            }
        }
        // 返回数据为其他类型
        return success;
    }
}
