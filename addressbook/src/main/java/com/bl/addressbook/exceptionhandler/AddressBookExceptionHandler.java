package com.bl.addressbook.exceptionhandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bl.addressbook.exceptions.UserNotFoundException;


@ControllerAdvice
public class AddressBookExceptionHandler {
	
	
	@Value(value= "User Not Found Exception")
	private String message1;
	
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException){
		return new ResponseEntity<String>(message1,HttpStatus.NOT_FOUND);
		
	}
}
