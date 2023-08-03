package com.chelex.phonebook.interceptor;

import com.chelex.phonebook.service.KafkaEventProducerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final KafkaEventProducerService kafkaEventProducerService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
        long startTime = System.currentTimeMillis();
        log.info("\n-------- LogInterception.preHandle --------");
        String requestMessage = "Request URL: " + request.getRequestURL();
        log.info(requestMessage);
        log.info("Start Time: " + System.currentTimeMillis());

        kafkaEventProducerService.sendMessage("preHandle --> " + requestMessage);

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
