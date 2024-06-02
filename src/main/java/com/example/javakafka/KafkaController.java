package com.example.javakafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class KafkaController {
    //Just for test
    private KafkaTemplate<String, String> template;
    private TopicConsumer myTopicConsumer;

    public KafkaController(KafkaTemplate<String, String> template, TopicConsumer myTopicConsumer) {
        this.template = template;
        this.myTopicConsumer = myTopicConsumer;
    }

    @PostMapping("/kafka/produce")
    public void produce(@RequestParam String message, @RequestParam Integer amount) {
        for(int i = 0; i < amount; i++) {
            CompletableFuture<SendResult<String, String>> futureMsg = template.send("JavaToKafka", message);
            futureMsg.whenComplete((result, ex) -> {
                if(ex == null){
                    System.out.println("Sent message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }
                else {
                    System.out.println("Unable to send message=[" +
                            message + "] due to : " + ex.getMessage());
                }
            });
        }
    }

    @GetMapping("/kafka/messages")
    public List<String> getMessages() {

        return myTopicConsumer.getMessages();
    }


}