package com.venturi.technology.librarysystem.repository;

import java.util.List;

import com.venturi.technology.librarysystem.model.dto.BookAndBorrowedBookDTO;

public interface CustomBookRepository {

	public List<BookAndBorrowedBookDTO> getBooksWithBorrowedBooksByBookIds(List<Long> bookIds);
	
}
