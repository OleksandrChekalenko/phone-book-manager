package com.chelex.phonebook.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
        long startTime = System.currentTimeMillis();
        log.info("\n-------- LogInterception.preHandle --------");
        log.info("Request URL: " + request.getRequestURL());
        log.info("Start Time: " + System.currentTimeMillis());

        request.setAttribute("startTime", startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler,
                           ModelAndView modelAndView) {

        log.info("\n-------- LogInterception.postHandle--------");
        log.info("Request URL: " + request.getRequestURL());

        // You can add attributes in the modelAndView
        // and use that in the view page
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) {
        log.info("\n-------- LogInterception.afterCompletion--------");

        long endTime = System.currentTimeMillis();
        long startTime = (Long) request.getAttribute("startTime");
        log.info("Request URL: " + request.getRequestURL());
        log.info("End Time: " + endTime);

        log.info("Time Taken: " + (endTime - startTime));
    }
}
