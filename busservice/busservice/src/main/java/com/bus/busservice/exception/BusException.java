package com.bus.busservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

	public BusException(String message) {
		super(message);
	}

	
    
}