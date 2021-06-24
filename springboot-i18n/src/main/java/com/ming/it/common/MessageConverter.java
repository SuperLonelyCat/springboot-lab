package com.ming.it.common;

import com.ming.it.constant.WebConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author 11119638
 * @date 2021/6/24 11:07
 */
@Component
public class MessageConverter {

    @Autowired
    private MessageSource messageSource;

    public String getMsg(int code) {

        return messageSource.getMessage(WebConstant.RESULT_CODE_PREFIX + code,
                null, LocaleContextHolder.getLocale());
    }

    public String getMsg(ResultCode resultCode, Object[] objects) {

        return messageSource.getMessage(WebConstant.RESULT_CODE_PREFIX + resultCode.getCode(),
                objects, LocaleContextHolder.getLocale());
    }
}
