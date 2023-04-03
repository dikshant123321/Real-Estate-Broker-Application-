package com.brokerApplication.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<String> errorMessages = new ArrayList();
        for (FieldError fieldError : fieldErrors) {
            String errorMessage = fieldError.getDefaultMessage();
            String fieldName = fieldError.getField();
            errorMessages.add(fieldName + " " + errorMessage);
        }

        ErrorResponse errorResponse = new ErrorResponse("Validation failed", errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        ErrorResponse errorResponse = new ErrorResponse("Data integrity violation", Collections.singletonList(ex.getMessage()));
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = ex.getMessage();
        String uniqueConstraintName = extractUniqueConstraintName(errorMessage);
        List<String> errors = Collections.singletonList("This value already exists in the database for the unique constraint " + uniqueConstraintName);
        ErrorResponse errorResponse = new ErrorResponse("Data integrity violation", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String extractUniqueConstraintName(String errorMessage) {
        Pattern pattern = Pattern.compile("unique constraint or index '(.+?)'");
        Matcher matcher = pattern.matcher(errorMessage);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "unknown";
    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> noHandlerFoundExceptionHandler(NoHandlerFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> exceptionExceptionHandler(Exception ex, WebRequest wbr){
//		
//		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//		
//	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<String> authorizationExceptionHandler(AuthorizationException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(BillingException.class)
	public ResponseEntity<String> billingExceptionHandler(BillingException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(BrokerException.class)
	public ResponseEntity<String> brokerExceptionHandler(BrokerException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> customerExceptionHandler(CustomerException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(DealException.class)
	public ResponseEntity<String> dealExceptionHandler(DealException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(PropertyException.class)
	public ResponseEntity<String> propertyExceptionHandler(PropertyException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(PropertyScheduleException.class)
	public ResponseEntity<String> propertyScheduleExceptionHandler(PropertyScheduleException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> userExceptionHandler(UserException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
