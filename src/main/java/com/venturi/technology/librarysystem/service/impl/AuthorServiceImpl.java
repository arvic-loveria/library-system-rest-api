package com.venturi.technology.librarysystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.venturi.technology.librarysystem.model.Author;
import com.venturi.technology.librarysystem.model.dto.AuthorDTO;
import com.venturi.technology.librarysystem.model.dto.BookDTO;
import com.venturi.technology.librarysystem.repository.AuthorRepository;
import com.venturi.technology.librarysystem.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorRepository authorRepository;

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public Author getBooksByAuthorId(Long authorId) {
		
		AuthorDTO authorDto = authorRepository.getBooksByAuthorId(authorId);
			
		/*
		 *  retrieve all available books based on the record from borrowed_book table.
		 *  If dateBorrowed is null, it means no record exist in borrowed_book table which means the book is available.
		 *  If dateBorrowed is not null and dateReturned is not null, it means there's a record in borrowed_book table
		 *  but was already returned, which means the book is available. Otherwise, the book is not available.
		*/
	 	List<BookDTO> availableBooks = authorDto.getBooks().stream()
				.filter(book -> (null == book.getDateBorrowed()
						|| (null != book.getDateBorrowed() && null != book.getDateReturned())))
				.collect(Collectors.toList());
	 	
	 	Author author = new Author();
		author.setAuthorId(authorDto.getAuthorId().longValue());
		author.setFirstName(authorDto.getAuthorFirstName());
		author.setMiddleName(authorDto.getAuthorMiddleName());
		author.setLastName(authorDto.getAuthorLastName());
		author.setBooks(availableBooks);
		
		return author;
	}

}
