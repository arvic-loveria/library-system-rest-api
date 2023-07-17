package com.venturi.technology.librarysystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.venturi.technology.librarysystem.entity.AuthorEntity;
import com.venturi.technology.librarysystem.entity.BorrowedBookEntity;
import com.venturi.technology.librarysystem.exception.AuthorNotFoundException;
import com.venturi.technology.librarysystem.model.Author;
import com.venturi.technology.librarysystem.model.BookAuthor;
import com.venturi.technology.librarysystem.repository.AuthorRepository;
import com.venturi.technology.librarysystem.repository.BorrowedBookRepository;
import com.venturi.technology.librarysystem.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	private BorrowedBookRepository borrowedBookRepository;
	private AuthorRepository authorRepository;

	public AuthorServiceImpl(BorrowedBookRepository borrowedBookRepository, AuthorRepository authorRepository) {
		this.borrowedBookRepository = borrowedBookRepository;
		this.authorRepository = authorRepository;
	}

	@Override
	public List<BookAuthor> getBookByAuthorId(Long authorId) {

		/*
		 * authorRepository.findById will generate the following SQL query:
		 * 
		 * select author_id, first_name, middle_name, last_name from author where author_id = ? 
		 * 
		 */
		AuthorEntity authorEntity = authorRepository.findById(authorId)
				.orElseThrow(() -> new AuthorNotFoundException("authorId: " + authorId + " not found"));

		List<BookAuthor> bookAuthors = new ArrayList<>();

		/*
		 * when authorEntity.getAuthorBooks() gets called, it will generate another query
		 * as this field was annotated as @OneToMany(mappedBy = "author") which used the default FetchType
		 * which is FetchType.Lazy. Generated query will be:
		 * 
		 * select a.author_book_id, a.author_id, b.book_id, b.book_name, b.book_description 
		 * from 
		 * 	author_book a
		 * left join 
		 * 	book b 
		 * 		on b.book_id = a.book_id
		 * where 
		 *  a.author_id = ?
		 *  
		 *  "left join book" was added because of AuthorBookEntity.book is annotated as 
		 *  @ManyToOne @JoinColumn(name = "book_id") which eagerly fetch the records of the related entity/table
		 *  due to the default FetchType of @ManyToOne annotation which is FetchType.EAGER
		 * 
		 */
		authorEntity.getAuthorBooks().forEach(authorBookEntity -> {
			
			/*
			 * borrowedBookRepository.findByBookBookIdAndDateReturnedIsNull(id) will generate the following query
			 * 
			 * select borrowed_id, book_id, student_id, date_borrowed, date_returned
			 * from borrowed_book
			 * where book_id = ?
			 * 	and date_returned IS NULL
			 * 
			 */
			Optional<BorrowedBookEntity> borrowedBookEntity = borrowedBookRepository
					.findByBookBookIdAndDateReturnedIsNull(authorBookEntity.getBook().getBookId());

			List<Author> authors = new ArrayList<>();

			if (!borrowedBookEntity.isPresent()) {
				BookAuthor bookAuthor = new BookAuthor();
				authors.add(new Author(authorBookEntity.getAuthor()));
				bookAuthor.setAuthors(authors);
				bookAuthor.setBookId(authorBookEntity.getBook().getBookId());
				bookAuthor.setBookName(authorBookEntity.getBook().getBookName());
				bookAuthors.add(bookAuthor);
			}
		});

		return bookAuthors;
	}

}
