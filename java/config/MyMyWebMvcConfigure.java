package com.example.demo.config;

import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import com.example.demo.interceptor.MyHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMyWebMvcConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration = registry.addInterceptor(myHandlerInterceptor());
        registration.addPathPatterns("/**")
                .excludePathPatterns("/submitLogin")
                .excludePathPatterns("/submitRegister")
                .excludePathPatterns("/content")
                .excludePathPatterns("/register")
                .excludePathPatterns("/captcha")
                .excludePathPatterns("/privacyPolicy")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/font/*")
                .excludePathPatterns("/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
    @Bean
    public MyHandlerInterceptor myHandlerInterceptor() {
        return new MyHandlerInterceptor();
    }

    @Bean
    public GifCaptcha gifCaptcha() {
        GifCaptcha gifCaptcha = new GifCaptcha(100, 35);
        gifCaptcha.setGenerator(new MathGenerator());
        return gifCaptcha;
    }
}
