package com.bl.employeepayroll.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public String message;

	public EmployeeNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	public EmployeeNotFoundException() {
		
	}
	
}
