package com.chelex.phonebook.controller.interceptor;

import com.chelex.phonebook.util.InterceptorEnableController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class PersonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {

        if (!InterceptorEnableController.isEnabled()) {
            return true;
        }

        log.info("\n-------- PersonInterceptor.preHandle --- ");
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler,
                           ModelAndView modelAndView) {

        log.info("\n-------- PersonInterceptor.postHandle --- ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) {

        log.info("\n-------- PersonInterceptor.afterCompletion --- ");
    }
}
