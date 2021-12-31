package com.bl.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import com.bl.rabbitmq.model.Message;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {
	
	@RabbitListener(queues="queue.A")
	private void receive (Message message) {
		log.info("Message Received From QUEUE->A ==",message);
	}
}
