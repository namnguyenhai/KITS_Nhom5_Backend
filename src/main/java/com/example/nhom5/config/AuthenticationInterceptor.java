package com.example.nhom5.config;

import com.example.nhom5.interceptor.AdminAuthenticationInterceptor;
import com.example.nhom5.interceptor.UserAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationInterceptor implements WebMvcConfigurer {

    @Autowired
    private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
    @Autowired
    private UserAuthenticationInterceptor userAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/api/admin/**");
//        registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/api/category/**");
        //registry.addInterceptor(userAuthenticationInterceptor).addPathPatterns("/api/user/**");

       // registry.addInterceptor(siteAuthenticationInterceptor).addPathPatterns("/site/**");
    }

}
