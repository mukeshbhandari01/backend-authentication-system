package com.Backend.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.Backend.Ecxeptions.UserAlreadyExistException;
import com.Backend.ErorrResponseClass.ErorrResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ErorrResponse> handle(UserAlreadyExistException ex){
		ErorrResponse erorr=new ErorrResponse(ex.getMessage(),HttpStatus.CONFLICT.value());
		 return new ResponseEntity<>(erorr, HttpStatus.CONFLICT);
	}
	

}
