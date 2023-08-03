package com.chelex.phonebook.interceptor;

import com.chelex.phonebook.domain.request.InterceptorDataRequest;
import com.chelex.phonebook.service.KafkaEventProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
        long startTime = System.currentTimeMillis();
        log.info("\n-------- LogInterception.preHandle --------");
        String requestMessage = "Request URL: " + request.getRequestURL();
        log.info(requestMessage);
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
                                @NotNull Object handler,
                                Exception ex) throws JsonProcessingException {
        log.info("\n-------- LogInterception.afterCompletion--------");

        long endTime = System.currentTimeMillis();
        long startTime = (Long) request.getAttribute("startTime");
        log.info("Request URL: " + request.getRequestURL());
        log.info("End Time: " + endTime);

        log.info("Time Taken: " + (endTime - startTime));
        kafkaEventProducerService.sendMessage(
                objectMapper.writeValueAsString(
                        InterceptorDataRequest.builder()
                                .requestUrl(request.getRequestURL().toString())
                                .requestTime(endTime - startTime)
                                .build()));
    }
}
