package com.venturi.technology.librarysystem.service;

import com.venturi.technology.librarysystem.model.Author;

public interface AuthorService {

	Author getBooksByAuthorId(Long authorId);
	
}
