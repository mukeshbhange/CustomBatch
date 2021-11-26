package com.bl.addressbook.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {

	private String message;
	private Object data;
	private Object token;


	public ResponseDTO(String message, Object empData) {
		this.message = message;
		this.data = empData;
	}

}


