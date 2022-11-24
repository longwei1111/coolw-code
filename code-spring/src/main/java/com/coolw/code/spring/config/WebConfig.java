package com.coolw.code.spring.config;

import com.coolw.code.spring.convert.DateConverter;
import com.coolw.code.spring.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/12 10:02
 * @since 1.0
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/auth/**");
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }
}
