package com.venturi.technology.librarysystem.exception;

public class AuthorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4149029498779720179L;

	private final String message;
	
	public AuthorNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
