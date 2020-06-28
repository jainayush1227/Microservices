package com.springboot.rest.application.exceptions;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = 177089180635292218L;

	public UserServiceException(String message) {
		super(message);
	}
	

}
