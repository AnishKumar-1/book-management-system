package com.bookmanagement.CustomException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> allExeption(Exception ex){
	    HttpStatus status;
	    ExceptionResponse exResp;

	    if (ex instanceof CustomResourceNotFound) {
	        status = HttpStatus.BAD_REQUEST;
	        exResp = new ExceptionResponse("Failed to process the request", ex.getLocalizedMessage(), status);
	    } else {
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	        exResp = new ExceptionResponse("An unexpected error occurred", ex.getLocalizedMessage(), status);
	    }
		
		
		return ResponseEntity.status(status).body(exResp);
	}

	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodLevelErrors(MethodArgumentNotValidException ex){
		Map<String,String> methodError=new HashMap<>();
		for(FieldError error:ex.getFieldErrors()) {
			String message=error.getField();
			String actualMessage=error.getDefaultMessage();
			methodError.put(message, actualMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(methodError);
	}
}
