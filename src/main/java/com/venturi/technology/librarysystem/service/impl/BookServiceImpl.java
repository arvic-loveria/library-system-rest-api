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
import com.venturi.technology.librarysystem.model.Book;
import com.venturi.technology.librarysystem.model.BorrowResponse;
import com.venturi.technology.librarysystem.model.dto.BookAndBorrowedBookDTO;
import com.venturi.technology.librarysystem.model.dto.BorrowedBookDTO;
import com.venturi.technology.librarysystem.repository.BookRepository;
import com.venturi.technology.librarysystem.repository.BorrowedBookRepository;
import com.venturi.technology.librarysystem.repository.StudentRepository;
import com.venturi.technology.librarysystem.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	private StudentRepository studentRepository;
	private BorrowedBookRepository borrowedBookRepository;

	public BookServiceImpl(BookRepository bookRepository, StudentRepository studentRepository, 
			BorrowedBookRepository borrowedBookRepository) {
		this.bookRepository = bookRepository;
		this.studentRepository = studentRepository;
		this.borrowedBookRepository = borrowedBookRepository;
	}

	@Override
	public Book getBookById(Long bookId) {

		/*
		 * bookRepository.findById(bookId) will generate below SQL query because BookEntity.borrowedBooks
		 * fetch type is set to FetchType.EAGER
		 * 
		 * 		select b.*, a.*, brb.*, s.*
		 * 		from book b
		 * 		left join author a
		 * 				on a.author_id = b.author_id
		 * 		left join borrowed_book brb
		 * 				on b.book_id = brb.book_id
		 * 		left join student s
		 * 				on s.student_id = brb.student_id
		 * 		where
		 * 			a.book_id = ?
		 *  
		 */
		BookEntity bookEntity = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("bookId: " + bookId + " not found"));

		Book bookAuthors = new Book();
		bookAuthors.setBookId(bookEntity.getBookId());
		bookAuthors.setBookName(bookEntity.getBookName());
		
		List<Author> authors = new ArrayList<>();
		authors.add(new Author(bookEntity.getAuthor()));
		bookAuthors.setAuthors(authors);

		return bookAuthors;
	}

	@Override
	public BorrowResponse processBorrowRequest(BorrowRequest borrowRequest) {

		/*
		 * studentRepository.findById(id) will generate the following SQL query:
		 * 
		 * select student_id, first_name, middle_name, last_name, email, birth_date from
		 * student where student_id = ?
		 * 
		 * No more additional query will be generated as the related Object on
		 * StudentEntity is not being called within this method block.
		 * 
		 */
		StudentEntity studentEntity = studentRepository.findById(borrowRequest.getStudentId()).orElseThrow(
				() -> new StudentNotFoundException("studentId: [" + borrowRequest.getStudentId() + "] not found"));

		List<BookAndBorrowedBookDTO> bookAndBorrowedBookDTOs = bookRepository
				.getBooksWithBorrowedBooksByBookIds(borrowRequest.getBookIds());

		List<BorrowBookResponse> notAvailableBooks = new ArrayList<>();
		List<BorrowBookResponse> availableBooks = new ArrayList<>();

		bookAndBorrowedBookDTOs.forEach(bookAndBorrowedBookDTO -> {

			/*
			 * return the first object which dateReturned equals null and dateBorrowed is not null to be 
			 * used in validation on whether the book is still available or not.
			*/
			Optional<BorrowedBookDTO> borrowedBookDto = bookAndBorrowedBookDTO.getBorrowedBooks().stream()
					.filter(borrowedBook -> (borrowedBook.getDateReturned() == null && borrowedBook.getDateBorrowed() != null))
					.findFirst();
						
			if (borrowedBookDto.isPresent()) {
				// add to not available book list
				BorrowBookResponse notAvailableBook = new BorrowBookResponse();
				notAvailableBook.setBookId(bookAndBorrowedBookDTO.getBookId().longValue());
				notAvailableBook.setBookName(bookAndBorrowedBookDTO.getBookName());
				notAvailableBook.setBookDescription(bookAndBorrowedBookDTO.getBookDescription());
				notAvailableBook.setReason("Book is not yet returned.");
				notAvailableBooks.add(notAvailableBook);

			} else {
				// add to list of books successfully borrowed.
				BorrowBookResponse availableBook = new BorrowBookResponse();
				availableBook.setBookId(bookAndBorrowedBookDTO.getBookId().longValue());
				availableBook.setBookName(bookAndBorrowedBookDTO.getBookName());
				availableBook.setBookDescription(bookAndBorrowedBookDTO.getBookDescription());
				availableBooks.add(availableBook);
				
				// insert into borrowed_book table as the book is available.
				BookEntity bookEntity = new BookEntity();
				bookEntity.setBookId(bookAndBorrowedBookDTO.getBookId().longValue());
				bookEntity.setBookName(bookAndBorrowedBookDTO.getBookName());
				bookEntity.setBookDescription(bookAndBorrowedBookDTO.getBookDescription());

				BorrowedBookEntity borrowedBookInsert = new BorrowedBookEntity();
				borrowedBookInsert.setBook(bookEntity);
				borrowedBookInsert.setDateBorrowed(LocalDateTime.now());
				borrowedBookInsert.setStudent(studentEntity);
				
				/*
				 * borrowedBookRepository.save(entity) will generate below SQL:
				 * 
				 * 	insert into borrowed_book (borrowed_id, book_id, date_borrowed, date_returned, student_id)
				 * 	values (default, ?, ?, ?, ?)
				 * 
				 */
				borrowedBookRepository.save(borrowedBookInsert);
			}

		});

		BorrowResponse borrowResponse = new BorrowResponse();
		borrowResponse.setNotAvailable(notAvailableBooks);
		borrowResponse.setBorrowed(availableBooks);

		return borrowResponse;
	}

}
