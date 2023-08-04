package com.chelex.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneBookKafkaConsumerService {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}",
                   groupId = "${spring.kafka.consumer.group}")
    public void consume(String message) throws JsonProcessingException {
//        InterceptorDataRequest data = objectMapper.readValue(message, InterceptorDataRequest.class);
//        log.info(data.toString());
        log.info(message);
    }
}
