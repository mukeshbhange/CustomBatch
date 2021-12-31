package com.bl.rabbitmq.controller;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bl.rabbitmq.model.Message;

@RestController
public class Producer {
	
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	@Autowired
	private DirectExchange exchange;
	
	
	@PostMapping("/send")
	public String send(@RequestBody Message message) {
		rabbitTemplate.convertAndSend(exchange.getName(),"routing.A",message);
		return "message Sent Successfully";
	}
}
