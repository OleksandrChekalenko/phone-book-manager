package com.chelex.phonebook.controller.interceptor;

import com.chelex.phonebook.domain.request.InterceptorDataRequest;
import com.chelex.phonebook.service.KafkaEventProducerService;
import com.chelex.phonebook.util.ColoredLog;
import com.chelex.phonebook.util.InterceptorEnableController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.chelex.phonebook.constant.AnsiColorCodes.ANSI_BLUE;
import static com.chelex.phonebook.constant.AnsiColorCodes.ANSI_PURPLE;

@Slf4j
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    public static final String REQUEST_URL = "Request URL: ";
    private final KafkaEventProducerService kafkaEventProducerService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
        if (!InterceptorEnableController.isEnabled()) {
            return true;
        }

        long startTime = System.currentTimeMillis();
        ColoredLog.info("\n-------- LogInterception.preHandle --------");
        String requestMessage = REQUEST_URL + request.getRequestURL();
        ColoredLog.log(ANSI_PURPLE, requestMessage);
        ColoredLog.log(ANSI_BLUE, "Start Time: " + System.currentTimeMillis());

        request.setAttribute("startTime", startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler,
                           ModelAndView modelAndView) {
        if (!InterceptorEnableController.isEnabled()) {
            return;
        }

        log.info("\n-------- LogInterception.postHandle--------");
        log.info(REQUEST_URL + request.getRequestURL());

        // You can add attributes in the modelAndView
        // and use that in the view page
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler,
                                Exception ex) throws JsonProcessingException {
        if (!InterceptorEnableController.isEnabled()) {
            return;
        }

        log.info("\n-------- LogInterception.afterCompletion--------");

        long endTime = System.currentTimeMillis();
        Object startTime = request.getAttribute("startTime");
        if (startTime == null) {
            return;
        }
        long startTimeMls = (Long) startTime;
        log.info(REQUEST_URL + request.getRequestURL());
        log.info("End Time: " + endTime);

        log.info("Time Taken: " + (endTime - startTimeMls));
        kafkaEventProducerService.sendMessage(
                objectMapper.writeValueAsString(
                        InterceptorDataRequest.builder()
                                .requestUrl(request.getRequestURL().toString())
                                .requestTime(endTime - startTimeMls)
                                .build()));
    }
}
