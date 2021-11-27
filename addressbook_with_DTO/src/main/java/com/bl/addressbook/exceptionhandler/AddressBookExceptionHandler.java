package com.bl.addressbook.exceptionhandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bl.addressbook.dto.ResponseDTO;
import com.bl.addressbook.exceptions.AddressBookExceptions;
import com.bl.addressbook.exceptions.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class AddressBookExceptionHandler {
	
	
	private static final String message ="Exception while processing REST request";

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseDTO> handleMessageNotReadableException(HttpMessageNotReadableException exception){
		
		log.error("Invalid For Format for User.",exception);
		ResponseDTO responsedto = new ResponseDTO(message,"Invalid Format of User data");
		return new ResponseEntity<ResponseDTO>(responsedto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		List<ObjectError> errorList =exception.getBindingResult().getAllErrors();
		List<String> errMsg =errorList.stream().map(objErr->objErr.getDefaultMessage()).collect(Collectors.toList());
		ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request",errMsg);
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
	}

	@Value(value= "User Not Found Exception")
	private String message1;
	
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException){
		return new ResponseEntity<String>(message1,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value=AddressBookExceptions.class)
	public ResponseEntity<String> handleAddressBookException(AddressBookExceptions exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
	}
}
