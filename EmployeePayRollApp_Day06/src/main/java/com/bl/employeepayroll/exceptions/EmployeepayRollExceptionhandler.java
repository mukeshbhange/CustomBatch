package com.bl.employeepayroll.exceptions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bl.employeepayroll.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class EmployeepayRollExceptionhandler {
	
	
	private static final String message ="Exception while processing REST request";

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseDTO> handleMessageNotReadableException(HttpMessageNotReadableException exception){
		
		log.error("Invalid date Format.",exception);
		ResponseDTO responsedto = new ResponseDTO(message,"Date should be in dd MMM yyyy format");
		return new ResponseEntity<ResponseDTO>(responsedto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		List<ObjectError> errorList =exception.getBindingResult().getAllErrors();
		List<String> errMsg =errorList.stream().map(objErr->objErr.getDefaultMessage()).collect(Collectors.toList());
		ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request",errMsg);
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ResponseDTO> handleEmployeeNotFoundException(NoSuchElementException exception){
		ResponseDTO responsedto = new ResponseDTO("Eception While Proceesg RestRequest",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responsedto,HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(EmployeeNoFound.class)
	public ResponseEntity<String> handleEmployeeNoFound(EmployeeNoFound exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
