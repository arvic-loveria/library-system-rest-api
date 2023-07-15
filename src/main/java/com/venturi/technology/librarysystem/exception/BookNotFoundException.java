package com.venturi.technology.librarysystem.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5554688479207210665L;

	private final String message;
	
	public BookNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
