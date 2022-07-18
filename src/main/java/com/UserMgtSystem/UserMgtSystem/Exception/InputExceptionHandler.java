package com.UserMgtSystem.UserMgtSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ ControllerAdvice
public class InputExceptionHandler {

	
	@ExceptionHandler(NameInpuException.class)
	public ResponseEntity<Object> mymessage(NameInpuException r){
		return new ResponseEntity<>(r.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		
		
	}

}
