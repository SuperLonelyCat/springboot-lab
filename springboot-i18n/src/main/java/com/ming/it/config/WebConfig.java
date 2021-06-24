package com.ming.it.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 11119638
 * @date 2021/6/23 10:24
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    // 注册通过请求参数(?lang=en_US)来判断当前语言环境
    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        // 默认为locale，配置为lang
        interceptor.setParamName(WebConstant.LANG_SIGN);
        registry.addInterceptor(interceptor);
    }
    */

    // 默认解析器为AcceptHeaderLocaleResolver，通过请求头中Accept-Language来判断当前语言环境
    // 设置会话区域解析器SessionLocaleResolver
    /*
    @Bean
    LocaleResolver localeResolver() {
        // 会话区域解析器
        // SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        // localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        // return localeResolver;
    }
     */

    // 自定义判断请求头参数
    /*
    @Bean
    public LocaleResolver localeResolver() {
        return new DefinedLocaleResolver();
    }
    */
}
