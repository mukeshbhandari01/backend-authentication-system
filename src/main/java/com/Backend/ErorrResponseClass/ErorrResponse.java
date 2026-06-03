package com.Backend.ErorrResponseClass;

import java.time.LocalDateTime;

public class ErorrResponse {
	
	private String message;
	private int Status;
    private LocalDateTime timestamp;
	
	public ErorrResponse(String m,int s) {
		this.message=m;
		this.Status=s;
	    this.timestamp = LocalDateTime.now();
	}
	
	public String getMessage() {
		return message;
	}
	public int getStatus() {
		return Status;
	}
	   public LocalDateTime getTimestamp() {
	        return timestamp;
	    }
}
