package com.venturi.technology.librarysystem.service;

import com.venturi.technology.librarysystem.mapping.BorrowRequest;
import com.venturi.technology.librarysystem.model.BookAuthor;
import com.venturi.technology.librarysystem.model.BorrowResponse;

public interface BookService {

	BookAuthor getBookById(Long bookId);
	
	BorrowResponse processBorrowRequest(BorrowRequest borrowRequest);
	
}
