package com.chelex;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class KafkaConsumer {
    public static void main(String[] args) {
//        SpringApplication.run(KafkaConsumer.class);
        new SpringApplicationBuilder(KafkaConsumer.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
