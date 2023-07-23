package com.venturi.technology.librarysystem.repository;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.venturi.technology.librarysystem.helper.BookAndBorrowedBookResultTransformer;
import com.venturi.technology.librarysystem.model.dto.BookAndBorrowedBookDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<BookAndBorrowedBookDTO> getBooksWithBorrowedBooksByBookIds(List<Long> bookIds) {

		List<BookAndBorrowedBookDTO> bookAndBorrowedBookDTOs = (List<BookAndBorrowedBookDTO>) em.createNativeQuery(
				"select b.book_id as bookId, b.book_name as bookName, b.book_description as bookDescription,"
						+ " c.student_id as studentId, c.date_borrowed as dateBorrowed, c.date_returned as dateReturned"
						+ " from book b left join borrowed_book c on b.book_id = c.book_id"
						+ " where b.book_id IN (:bookIds)")
				.setParameter("bookIds", bookIds).unwrap(Query.class)
				.setTupleTransformer(new BookAndBorrowedBookResultTransformer()).getResultList();

		return bookAndBorrowedBookDTOs;
	}

}
