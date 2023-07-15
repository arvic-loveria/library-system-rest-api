package com.venturi.technology.librarysystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venturi.technology.librarysystem.model.BookAuthor;
import com.venturi.technology.librarysystem.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	private AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("/{authorId}/books")
	public ResponseEntity<List<BookAuthor>> getBooksByAuthorId(@PathVariable("authorId") Long authorId){
		
		return ResponseEntity.ok(authorService.getBookByAuthorId(authorId));
	}
	
}
