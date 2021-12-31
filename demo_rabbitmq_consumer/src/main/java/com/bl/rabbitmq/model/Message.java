package com.bl.rabbitmq.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {
	
	
	private int id;
	private String message;

}
