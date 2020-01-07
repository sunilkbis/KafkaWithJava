package com.okta.javakafka.controller;

import java.util.List;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.okta.javakafka.consumer.TestConsumer;
import com.okta.javakafka.entity.Employee;

@RestController
public class KafkaController {

	private KafkaTemplate<String, String> template;
	private TestConsumer testConsumer;
	
	/**
	 * 
	 * @param template to sending a message to topic
	 * @param testConsumer consumes every changes
	 */
	public KafkaController(KafkaTemplate<String, String> template, TestConsumer testConsumer) {
		this.template = template;
		this.testConsumer = testConsumer;
	}
	
	/**
	 * 
	 * @param message, producer posts message
	 */
	@PostMapping("/kafka/produce")
	public void produce(@RequestBody List<Employee> employees) {
		template.send("test", employees.toString());
	}
	
	/**
	 * 
	 * @return consumed messages 
	 */
	@GetMapping("/kafka/messages")
	    public Map<String, List<Employee>> getMessages() {
	        return testConsumer.getMessages();
	    }
}
