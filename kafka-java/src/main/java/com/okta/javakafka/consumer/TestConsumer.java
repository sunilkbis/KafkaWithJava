package com.okta.javakafka.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.javakafka.entity.Employee;

@Component
public class TestConsumer {
    private final List<String> messages = new ArrayList<>();
    
    @KafkaListener(topics = "test", groupId = "kafka-sandbox")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }
    public List<Employee> getMessages() throws JsonMappingException, JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	List<Employee> employees = new ArrayList<>();
    	Employee employee = null;
    	for(String message : messages ) {
    		 System.out.println(message);
    		 employee = objectMapper.readValue(message, Employee.class);
    		 System.out.println(employee);
    		 employees.add(employee);
    	}
    	
       
    	return employees;
    }
}
