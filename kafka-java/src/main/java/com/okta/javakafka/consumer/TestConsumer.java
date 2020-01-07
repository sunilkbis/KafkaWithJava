package com.okta.javakafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.okta.javakafka.entity.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TestConsumer {
    private final Map<String, List<Employee>> messages = new HashMap<>();
    
    @KafkaListener(topics = "test", groupId = "kafka-sandbox")
    public void listen(List<Employee> message) {
        synchronized (messages) {
            messages.put("Message",message);
        }
    }
    public Map<String, List<Employee>> getMessages() {
        return messages;
    }
}
