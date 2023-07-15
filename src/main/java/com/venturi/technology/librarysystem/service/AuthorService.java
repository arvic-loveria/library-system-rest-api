package com.venturi.technology.librarysystem.service;

import java.util.List;

import com.venturi.technology.librarysystem.model.BookAuthor;

public interface AuthorService {

	List<BookAuthor> getBookByAuthorId(Long authorId);
	
}
