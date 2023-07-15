package com.venturi.technology.librarysystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.venturi.technology.librarysystem.mapping.ApiError;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	protected ResponseEntity<Object> handleStudentNotFound(StudentNotFoundException ex, WebRequest request) {

		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
	}
	
	@ExceptionHandler(AuthorNotFoundException.class)
	protected ResponseEntity<Object> handleAuthorNotFound(AuthorNotFoundException ex, WebRequest request) {

		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	protected ResponseEntity<Object> handleBookNotFound(BookNotFoundException ex, WebRequest request) {

		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
