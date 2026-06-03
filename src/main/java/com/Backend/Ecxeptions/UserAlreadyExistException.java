package com.Backend.Ecxeptions;

public class UserAlreadyExistException extends RuntimeException{
	public UserAlreadyExistException(String message) {
		super(message);
		
	}
}
