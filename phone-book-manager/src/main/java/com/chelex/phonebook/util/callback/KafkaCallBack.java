package com.chelex.phonebook.util.callback;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class KafkaCallBack implements Callback {

    private final String messageKey;

    //Set message key to identify message. Additional information
    //can also be set here to provide context
    public KafkaCallBack(String messageKey) {
        super();
        this.messageKey = messageKey;
    }

    @Override
    public void onCompletion(RecordMetadata retData, Exception e) {

        //Check if exception occurred
        if (e != null) {
            log.info("Exception Publishing Asynchronously without Callback :"
                    + "Message Key = " + messageKey + " : " + e.getMessage());
        } else {
            log.info(" Callback received for Message Key " + messageKey
                    + " returned Partition : " + retData.partition()
                    + " and Offset : " + retData.offset());
        }
    }
}
