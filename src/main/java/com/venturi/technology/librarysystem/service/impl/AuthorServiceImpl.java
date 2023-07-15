package com.venturi.technology.librarysystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.venturi.technology.librarysystem.entity.AuthorBookEntity;
import com.venturi.technology.librarysystem.entity.BorrowedBookEntity;
import com.venturi.technology.librarysystem.exception.AuthorNotFoundException;
import com.venturi.technology.librarysystem.model.Author;
import com.venturi.technology.librarysystem.model.BookAuthor;
import com.venturi.technology.librarysystem.repository.AuthorBookRepository;
import com.venturi.technology.librarysystem.repository.BorrowedBookRepository;
import com.venturi.technology.librarysystem.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorBookRepository authorBookRepository;
	private BorrowedBookRepository borrowedBookRepository;

	public AuthorServiceImpl(AuthorBookRepository authorBookRepository, BorrowedBookRepository borrowedBookRepository) {
		this.authorBookRepository = authorBookRepository;
		this.borrowedBookRepository = borrowedBookRepository;
	}

	@Override
	public List<BookAuthor> getBookByAuthorId(Long authorId) {

		List<AuthorBookEntity> authorBookEntities = authorBookRepository.findByAuthorAuthorId(authorId)
				.orElseThrow(() -> new AuthorNotFoundException("authorId: " +authorId + " not found"));

		List<BookAuthor> bookAuthors = new ArrayList<>();

		authorBookEntities.forEach(authorBookEntity -> {
			Optional<BorrowedBookEntity> borrowedBookEntity = borrowedBookRepository
					.findByBookBookIdAndDateReturnedIsNull(authorBookEntity.getBook().getBookId());
			if(!borrowedBookEntity.isPresent()) {
				BookAuthor bookAuthor = new BookAuthor();
				bookAuthor.setAuthor(new Author(authorBookEntity.getAuthor()));
				bookAuthor.setBookId(authorBookEntity.getBook().getBookId());
				bookAuthor.setBookName(authorBookEntity.getBook().getBookName());
				bookAuthors.add(bookAuthor);
			}
		});

		return bookAuthors;
	}

}
