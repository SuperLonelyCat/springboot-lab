package com.ming.it.config;

import com.ming.it.constant.WebConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;

/**
 * @author 11119638
 * @date 2021/6/24 11:30
 */
public class DefinedLocaleResolver implements LocaleResolver {

    // 自定义处理器获取请求头
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        final String lang = request.getHeader(WebConstant.ACCEPT_LANGUAGE);
        if (StringUtils.isBlank(lang)){
            return Locale.getDefault();
        }
        final String[] langs = lang.split(WebConstant.JOINER_SYMBOL);
        return Objects.equals(NumberUtils.INTEGER_ONE, langs.length) ? switchLanguage(langs[NumberUtils.INTEGER_ZERO])
                : new Locale(langs[NumberUtils.INTEGER_ZERO], langs[NumberUtils.INTEGER_ONE]);
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }

    private Locale switchLanguage(String lang) {
        return Locale.ENGLISH.getLanguage().equals(lang) ? Locale.US : new Locale(lang);
    }
}
