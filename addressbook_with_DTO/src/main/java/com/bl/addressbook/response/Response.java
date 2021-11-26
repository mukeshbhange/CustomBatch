package com.bl.addressbook.response;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class Response {
	private long statusCode;
	private String message;
	private Object token;
	
	public Response() {
		
	}
}
