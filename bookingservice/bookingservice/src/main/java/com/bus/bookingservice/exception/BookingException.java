package com.bus.bookingservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

public class BookingException extends RuntimeException{
	private static final long serialVersionUID = 1L;


	public BookingException(String message) {
		super(message);
	}
}
