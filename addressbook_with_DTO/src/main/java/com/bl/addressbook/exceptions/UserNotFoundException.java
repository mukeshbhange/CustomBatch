package com.bl.addressbook.exceptions;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3797656260593222737L;
	public long errorCode;
	public String message;
	
	public UserNotFoundException() {
		
	}
}
