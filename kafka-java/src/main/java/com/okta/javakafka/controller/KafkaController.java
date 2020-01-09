package com.okta.javakafka.controller;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.javakafka.consumer.TestConsumer;
import com.okta.javakafka.entity.Employee;

@RestController
public class KafkaController {

	private KafkaTemplate<String, String> template;
	private TestConsumer testConsumer;

	/**
	 * 
	 * @param template     to sending a message to topic
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
	// @RequestMapping(consumes="application/json")
	public void produce(@RequestBody List<Employee> employees) {
		
		ObjectMapper mapper = new ObjectMapper();
	
		for (Employee employee : employees) {
		
			String employeeString = null;
			
			try {
				employeeString = mapper.writeValueAsString(employee);
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			System.out.println(employeeString);
			template.send("test", employeeString);
		}
		
	}

	/**
	 * 
	 * @return consumed messages
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	@GetMapping("/kafka/messages")
	// @RequestMapping(produces="application/json")
	public List<Employee> getMessages() throws JsonMappingException, JsonProcessingException {
		return testConsumer.getMessages();
	}
}
