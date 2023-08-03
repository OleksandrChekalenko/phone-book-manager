package com.chelex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhoneBookKafkaConsumerService implements CommandLineRunner {

    @KafkaListener(topics = "phone-book-manager-topic",
                   groupId = "${spring.kafka.consumer.group}")
    public void consume(String message) {
        log.error("-------------------Start message-------------------");

        log.info(message);

        log.error("-------------------End message-------------------");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
