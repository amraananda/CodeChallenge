package com.disney.studios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(VoteAlreadyExistsException.class)
	public ResponseEntity<String> handleVoteAlreadyExistsException(VoteAlreadyExistsException ve){
		return new ResponseEntity<String>(ve.getMessage(),HttpStatus.NOT_MODIFIED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ae){
		return new ResponseEntity<String>(ae.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
