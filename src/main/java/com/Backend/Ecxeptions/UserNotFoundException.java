package com.Backend.Ecxeptions;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
		super(message);
		
	}

}
