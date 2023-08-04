package com.chelex.phonebook.cofig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Value("${spring.kafka.topic}")
    private String topic;

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(topic)
                .partitions(2)
                .replicas(2)
                .build();
    }
}
