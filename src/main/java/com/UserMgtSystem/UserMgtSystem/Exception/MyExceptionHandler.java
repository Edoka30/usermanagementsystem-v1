package com.UserMgtSystem.UserMgtSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(EmailValidationException.class)
	public ResponseEntity<Object> mymessage(EmailValidationException r){
		return new ResponseEntity<>(r.getMessage(), HttpStatus.BAD_REQUEST);
		
		
	}

}
