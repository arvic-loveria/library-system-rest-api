package com.venturi.technology.librarysystem.service;

import com.venturi.technology.librarysystem.mapping.BorrowRequest;
import com.venturi.technology.librarysystem.model.Book;
import com.venturi.technology.librarysystem.model.BorrowResponse;

public interface BookService {

	Book getBookById(Long bookId);
	
	BorrowResponse processBorrowRequest(BorrowRequest borrowRequest);
	
}
