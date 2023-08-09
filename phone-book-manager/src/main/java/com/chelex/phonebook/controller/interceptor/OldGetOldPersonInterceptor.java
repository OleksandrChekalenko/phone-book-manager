package com.chelex.phonebook.controller.interceptor;

import com.chelex.phonebook.util.InterceptorEnableController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class OldGetOldPersonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             @NotNull Object handler) throws IOException {
        if (!InterceptorEnableController.isEnabled()) {
            return true;
        }

        log.info("\n-------- OldGetOldPersonInterceptor.preHandle --- ");
        log.info("Request URL: " + request.getRequestURL());
        log.info("Sorry! This URL is no longer used, Redirect to /api/v1/persons/getAll");

        response.sendRedirect(request.getContextPath() + "/api/v1/persons/getAll");
        return false;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler,
                           ModelAndView modelAndView) {

        // This code will never be run.
        log.info("\n-------- OldGetOldPersonInterceptor.postHandle --- ");
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler,
                                Exception ex) {

        // This code will never be run.
        log.info("\n-------- QueryStringInterceptor.afterCompletion --- ");
    }
}
