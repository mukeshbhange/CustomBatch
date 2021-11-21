package com.bl.employeepayroll.dto;

import lombok.Data;

@Data
public class ResponseDTO {
	
	private String message;
	private Object data;
	
	
	public ResponseDTO(String message, Object empData) {
		super();
		this.message = message;
		this.data = empData;
	}
	
	

}
