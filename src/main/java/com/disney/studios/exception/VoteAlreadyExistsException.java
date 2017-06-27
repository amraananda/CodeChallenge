package com.disney.studios.exception;

public class VoteAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public VoteAlreadyExistsException(String message){
		super(message);
	}

}
