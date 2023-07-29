package com.chelex.phonebook.cofig;

import com.chelex.phonebook.interceptor.OldGetOldPersonInterceptor;
import com.chelex.phonebook.interceptor.PersonInterceptor;
import com.chelex.phonebook.interceptor.LogInterceptor;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class InterceptorConfiguration extends WebMvcConfig {

    private static final String BASE_PATH = "/api/v1";

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        generalInterceptorsSetup(registry);
        personControllerInterceptorsSetup(registry);
    }

    private void generalInterceptorsSetup(InterceptorRegistry registry) {
        // LogInterceptor apply to all URLs.
        registry.addInterceptor(new LogInterceptor());
    }

    private void personControllerInterceptorsSetup(InterceptorRegistry registry) {
        // Old GET getAll persons url, no longer use.
        // Use OldGetOldPersonInterceptor to redirect to a new URL.
        registry.addInterceptor(new OldGetOldPersonInterceptor())//
                .addPathPatterns(BASE_PATH + "/person/all");

        // This interceptor apply to URL like /api/v1/persons/*
        // Exclude /api/v1/persons/getAll
        registry.addInterceptor(new PersonInterceptor())//
                .addPathPatterns(BASE_PATH + "/persons/*")//
                .excludePathPatterns(BASE_PATH + "/persons/getAll");
    }
}
