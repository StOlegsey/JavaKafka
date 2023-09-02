package com.example.javakafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Component
public class TopicConsumer {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "JavaKafka2", groupId = "kafka-sandbox")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }
    public List<String> getMessages() {
        return messages;
    }

}