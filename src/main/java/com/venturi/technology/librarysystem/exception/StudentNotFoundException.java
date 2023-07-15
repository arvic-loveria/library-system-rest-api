package com.venturi.technology.librarysystem.exception;

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -550440981295226684L;
	
	private final String message;

	public StudentNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
