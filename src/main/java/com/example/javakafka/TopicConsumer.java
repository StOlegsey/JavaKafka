package com.example.javakafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.kafka.support.KafkaHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Component
public class TopicConsumer {


    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "JavaToKafka", groupId = "kafka-group")
    public void listen1(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        /*synchronized (messages) {
            messages.add(message);
        }*/
        System.out.println("Consumer [1] received Message in group kafka-group: " + message + "from partition: " + partition);
    }

    /*@KafkaListener(topics = "JavaToKafka", groupId = "kafka-group")
    public void listen2(String message) {
        //synchronized (messages) {
        //    messages.add(message);}
        System.out.println("Consumer [2] received Message in group kafka-group: " + message);
    }*/

    public List<String> getMessages() {
        return messages;
    }

}