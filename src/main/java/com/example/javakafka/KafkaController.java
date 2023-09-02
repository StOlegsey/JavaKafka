package com.example.javakafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaController {

    private KafkaTemplate<String, String> template;
    private TopicConsumer myTopicConsumer;

    public KafkaController(KafkaTemplate<String, String> template, TopicConsumer myTopicConsumer) {
        this.template = template;
        this.myTopicConsumer = myTopicConsumer;
    }

    @GetMapping("/kafka/produce")
    public void produce(@RequestParam String message, @RequestParam Integer amount) {
        for(int i = 0; i < amount; i++) {
            template.send("JavaKafka2", message);
        }
    }

    @GetMapping("/kafka/messages")
    public List<String> getMessages() {

        return myTopicConsumer.getMessages();
    }


}