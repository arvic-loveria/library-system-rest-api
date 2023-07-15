package com.venturi.technology.librarysystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venturi.technology.librarysystem.mapping.BorrowRequest;
import com.venturi.technology.librarysystem.model.BookAuthor;
import com.venturi.technology.librarysystem.model.BorrowResponse;
import com.venturi.technology.librarysystem.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<BookAuthor> getBookById(@PathVariable("bookId") Long bookId) {
		
		return ResponseEntity.ok(bookService.getBookById(bookId));
	}
	
	@PostMapping
	public ResponseEntity<BorrowResponse> processBorrowBook(@RequestBody BorrowRequest borrowRequest) {
		
		return ResponseEntity.ok(bookService.processBorrowRequest(borrowRequest));
		
	}
}
