package com.ait.pbm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContactControllerAdvice {
	
	@ExceptionHandler(value=NoDataFoundException.class)
	public ResponseEntity<?> handleNoDataFoundException(NoDataFoundException ndfe) {
		return new ResponseEntity<String>(ndfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
