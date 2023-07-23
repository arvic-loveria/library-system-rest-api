package com.venturi.technology.librarysystem.repository;

import com.venturi.technology.librarysystem.model.dto.AuthorDTO;

public interface CustomAuthorRepository {

	public AuthorDTO getBooksByAuthorId(Long authorId);
	
}
