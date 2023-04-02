package com.brokerApplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.brokerApplication.entities.Customer;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<String> test(MethodArgumentNotValidException exp,WebRequest q){
//		return new ResponseEntity<String>(exp.,HttpStatus.BAD_REQUEST);
//		
//	}

}
