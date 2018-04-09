package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Web 配置信息类
 */
@Configuration // 说明此类是一个配置类
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * Cookie 区域解析器
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        // 设置默认区域
        localeResolver.setDefaultLocale(Locale.CHINA);
        // 设置 Cookie 有效期
        localeResolver.setCookieMaxAge(3600);
        localeResolver.setCookieName("Language");
        return localeResolver;
    }

    /**
     * 拦截器
     *
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        // 设置请求地址的参数 默认：Locale - carlist?Locale=en_US
        interceptor.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);

        // 自定义参数名： carlist?lang=zh_CN
        // interceptor.setParamName("lang")
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 把拦截器 Bean 加入到配置类中
        registry.addInterceptor(localeChangeInterceptor());
    }
}
