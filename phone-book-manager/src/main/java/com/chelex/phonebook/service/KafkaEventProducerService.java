package com.chelex.phonebook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaEventProducerService {

    private static final int BOUND = 1000;

    @Value("${spring.kafka.topic}")
    private String topic;
    //Use a Random number to generate message keys
    private final Random randomKey = new Random();

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Kafka message: " + message);
        kafkaTemplate.send(topic, "interceptor", message);
    }

    public void sendDifferentMessages() {
        /*
         Publish Asynchronously without any checks
         */
        //Create the record
        ProducerRecord<String, String> asyncNoChecksRec =
                new ProducerRecord<>(
                        topic,    //Topic name
                        String.valueOf(randomKey.nextInt(BOUND)),
                        "This is order published asynchronously with NO checks"
                );

        try {
            //No checks used
            kafkaTemplate.send(asyncNoChecksRec);

            log.info("\nSent Asynchronously, with no Checks :" + asyncNoChecksRec);

        } catch (Exception e) {
            log.info("Exception Publishing Asynchronously without Checks :" + e.getMessage());
        }

        //Publish Synchronously and check for results
        //Create the record
        ProducerRecord<String, String> syncRec =
                new ProducerRecord<>(
                        topic,    //Topic name
                        String.valueOf(randomKey.nextInt(BOUND)),
                        "This is order published synchronously"
                );

        //Send synchronously, wait for confirmation
        try {
            RecordMetadata retData = kafkaTemplate
                    .send(syncRec)
                    .get().getRecordMetadata(); //Get makes it synchronous

            log.info("\nSent Synchronously :" + syncRec
                    + " Received Partition : " + retData.partition()
                    + " and Offset : " + retData.offset());

        } catch (InterruptedException | ExecutionException e) {
            log.info("Exception Publishing Synchronously:" + e.getMessage());
        }
    }
}
