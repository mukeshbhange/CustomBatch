package com.bl.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler{

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<String>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
		return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<String> userAlreadyExistsException(LoginException loginException) {
		return new ResponseEntity<String>(loginException.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
		return new ResponseEntity<>("DB Connection Lost", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
