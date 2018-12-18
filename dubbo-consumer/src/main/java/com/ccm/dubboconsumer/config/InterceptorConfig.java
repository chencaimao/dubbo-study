package com.ccm.dubboconsumer.config;

import com.ccm.dubboconsumer.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by chencm on 2018/12/4
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean //将自定义拦截器注册到spring bean中，解决拦截器在springcontext之前加载，导致配置文件值没法注入问题
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor());
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/logout");
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/login.html");
        loginRegistry.excludePathPatterns("/js/**.js");
//        loginRegistry.excludePathPatterns("/js/jquery-3.3.1.min.js");
//        loginRegistry.excludePathPatterns("/js/amq_jquery_adapter.js");
//        loginRegistry.excludePathPatterns("/js/amq.js");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html#!");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/html/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

}
