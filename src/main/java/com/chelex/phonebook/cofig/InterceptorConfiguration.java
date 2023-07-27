package com.chelex.phonebook.cofig;

import com.chelex.phonebook.interceptor.OldGetOldPersonInterceptor;
import com.chelex.phonebook.interceptor.PersonInterceptor;
import com.chelex.phonebook.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class InterceptorConfiguration extends WebMvcConfig {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LogInterceptor apply to all URLs.
        registry.addInterceptor(new LogInterceptor());

        // Old GET getAll persons url, no longer use.
        // Use OldGetOldPersonInterceptor to redirect to a new URL.
        registry.addInterceptor(new OldGetOldPersonInterceptor())//
                .addPathPatterns("/api/v1/person/all");

        // This interceptor apply to URL like /api/v1/persons/*
        // Exclude /api/v1/persons/getAll
        registry.addInterceptor(new PersonInterceptor())//
                .addPathPatterns("/api/v1/persons/*")//
                .excludePathPatterns("/api/v1/persons/getAll");
    }
}
