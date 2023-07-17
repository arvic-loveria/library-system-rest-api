package com.venturi.technology.librarysystem.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.venturi.technology.librarysystem.entity.BookEntity;
import com.venturi.technology.librarysystem.entity.BorrowedBookEntity;
import com.venturi.technology.librarysystem.entity.StudentEntity;
import com.venturi.technology.librarysystem.exception.BookNotFoundException;
import com.venturi.technology.librarysystem.exception.StudentNotFoundException;
import com.venturi.technology.librarysystem.mapping.BorrowBookResponse;
import com.venturi.technology.librarysystem.mapping.BorrowRequest;
import com.venturi.technology.librarysystem.model.Author;
import com.venturi.technology.librarysystem.model.BookAuthor;
import com.venturi.technology.librarysystem.model.BorrowResponse;
import com.venturi.technology.librarysystem.repository.BookRepository;
import com.venturi.technology.librarysystem.repository.BorrowedBookRepository;
import com.venturi.technology.librarysystem.repository.StudentRepository;
import com.venturi.technology.librarysystem.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	private BorrowedBookRepository borrowedBookRepository;
	private StudentRepository studentRepository;

	public BookServiceImpl(BookRepository bookRepository, BorrowedBookRepository borrowedBookRepository,
			StudentRepository studentRepository) {
		this.bookRepository = bookRepository;
		this.borrowedBookRepository = borrowedBookRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public BookAuthor getBookById(Long bookId) {

		/*
		 * bookRepository.findById(bookId) will generate below SQL query
		 * 		select book_id, book_name, book_description from book where book_id = ?
		 * 
		 * and because the related object (authorBookEntity.getAuthor()) of BookEntity is also being called within this method,
		 * another SQL query like below will be generated only after the related object was called due to default FetchType 
		 * of @OneToMany which is FetchType.Lazy.
		 * 
		 * 		select a.author_book_id, a.book_id, b.author_id, b.first_name, b.middle_name, b.last_name
		 * 		from 
		 * 				author_book a
		 * 		left join 
		 * 				author b
		 * 					on b.author_id = a.author_id
		 * 		where
		 * 				a.book_id = ?
		 * 
		 * "left join author" was added because of AuthorBookEntity.author which is annotated as 
		 *  @ManyToOne @JoinColumn(name = "author_id") which eagerly fetch the records of the related entity/table
		 *  due to the default FetchType of @ManyToOne annotation which is FetchType.EAGER
		 * 
		 */
		BookEntity bookEntity = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("bookId: " + bookId + " not found"));

		BookAuthor bookAuthors = new BookAuthor();
		bookAuthors.setBookId(bookEntity.getBookId());
		bookAuthors.setBookName(bookEntity.getBookName());
		
		List<Author> authors = new ArrayList<>();
		bookEntity.getAuthorBooks().forEach(authorBookEntity -> {
			authors.add(new Author(authorBookEntity.getAuthor()));
		});
		bookAuthors.setAuthors(authors);

		return bookAuthors;
	}

	@Override
	public BorrowResponse processBorrowRequest(BorrowRequest borrowRequest) {

		List<Long> notAvailableBooks = new ArrayList<>();
		List<Long> availableBooks = new ArrayList<>();
		
		/*
		 * studentRepository.findById(id) will generate the following SQL query:
		 * 
		 * select student_id, first_name, middle_name, last_name, email, birth_date
		 * from student
		 * where student_id = ?
		 * 
		 * No more additional query will be generated as the related Object on StudentEntity is not being called
		 * within this method block.
		 * 
		 */
		StudentEntity studentEntity = studentRepository.findById(borrowRequest.getStudentId())
				.orElseThrow(() -> new StudentNotFoundException("studentId: " + borrowRequest.getStudentId() + " not found"));

		borrowRequest.getBookIds().forEach(bookId -> {
			
			/*
			 * borrowedBookRepository.findByBookBookIdAndDateReturnedIsNull(id) will generate the following SQL query:
			 * 
			 * select borrowed_id, date_borrowed, date_returned, student_id, book_id 
			 * from 
			 * 		borrowed_book
			 * where book_id = ?
			 * 	 	 and date_returned IS NULL
			 * 
			 */
			
			Optional<BorrowedBookEntity> borrowedBookEntity = borrowedBookRepository
					.findByBookBookIdAndDateReturnedIsNull(bookId);

			if (borrowedBookEntity.isPresent()) {
				notAvailableBooks.add(bookId);
			} else {
				availableBooks.add(bookId);
			}

		});
		
		// get all available books for preparation to insert in Borrowed_Book table
		
		/*
		 * bookRepository.findAllById(bookIds) will generate the following query
		 * 
		 * select book_id, book_name, book_description from book where book_id IN (?, ?)
		 * 
		 * 
		 */
	 	List<BookEntity> availableBookEntities = bookRepository.findAllById(availableBooks);
	 	availableBookEntities.forEach(availableBook -> {
	 		BorrowedBookEntity borrowedBookEntity = new BorrowedBookEntity();
	 		borrowedBookEntity.setBook(availableBook);
	 		borrowedBookEntity.setDateBorrowed(LocalDateTime.now());
	 		borrowedBookEntity.setStudent(studentEntity);
	 		
	 		/*
	 		 * borrowedBookRepository.save(entity) will generate below SQL query
	 		 * 
	 		 * insert into borrowed_book (book_id, date_borrowed, date_returned, student_id, borrowed_id)
	 		 * values (?, ?, ?, ?, default)
	 		 * 
	 		 */
	 		borrowedBookRepository.save(borrowedBookEntity);
	 	});
	 	
	 	
	 	BorrowResponse borrowResponse = new BorrowResponse();
	 	borrowResponse.setNotAvailable(getBorrowBooks(notAvailableBooks, true));
	 	borrowResponse.setBorrowed(getBorrowBooks(availableBooks, false));
		
		return borrowResponse;
	}

	private List<BorrowBookResponse> getBorrowBooks(List<Long> bookIds, boolean isSetReason) {
		
		/*
		 * bookRepository.findAllById(bookIds) will generate below SQL query
		 * 		select book_id, book_name, book_description from book where book_id IN (?, ?)
		 * 
		 * and because the related object (authorBookEntity.getAuthor()) of BookEntity is also being called within this method,
		 * another SQL query like below will be generated only after the related object was called due to default FetchType 
		 * of @OneToMany which is FetchType.Lazy.
		 * 
		 * 		select a.author_book_id, a.book_id, b.author_id, b.first_name, b.middle_name, b.last_name
		 * 		from 
		 * 				author_book a
		 * 		left join 
		 * 				author b
		 * 					on b.author_id = a.author_id
		 * 		where
		 * 				a.book_id = ?
		 * 
		 * "left join author" was added because of AuthorBookEntity.author which is annotated as 
		 *  @ManyToOne @JoinColumn(name = "author_id") which eagerly fetch the records of the related entity/table
		 *  due to the default FetchType of @ManyToOne annotation which is FetchType.EAGER
		 * 
		 */

		List<BookEntity> bookEntities = bookRepository.findAllById(bookIds);
		List<BorrowBookResponse> borrowBooks = new ArrayList<>();
		bookEntities.forEach(bookEntity -> {
			BorrowBookResponse borrowBookResponse = new BorrowBookResponse();
			borrowBookResponse.setBookId(bookEntity.getBookId());
			borrowBookResponse.setBookName(bookEntity.getBookName());
			
			List<Author> authors = new ArrayList<>();
			bookEntity.getAuthorBooks().forEach(authorBookEntity -> {
				authors.add(new Author(authorBookEntity.getAuthor()));
			});
			borrowBookResponse.setAuthors(authors);
			if (isSetReason) {
				borrowBookResponse.setReason("Book is not yet returned.");
			}
			borrowBooks.add(borrowBookResponse);
		});

		return borrowBooks;
	}

}
