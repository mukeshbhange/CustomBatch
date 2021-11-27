package com.bl.addressbook.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressBookExceptions extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7156858546047738725L;
	public String message;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
