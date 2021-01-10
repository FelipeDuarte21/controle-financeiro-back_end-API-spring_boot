package com.felipeduarte.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg) {
		super(msg);
	}
	

}
