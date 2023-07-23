package com.venturi.technology.librarysystem.repository;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.venturi.technology.librarysystem.exception.AuthorNotFoundException;
import com.venturi.technology.librarysystem.helper.AuthorDTOResultTransformer;
import com.venturi.technology.librarysystem.model.dto.AuthorDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public AuthorDTO getBooksByAuthorId(Long authorId) {
		
		try {
			AuthorDTO authorDto = (AuthorDTO) em.createNativeQuery(
					"select a.author_id as authorId, a.first_name as authorFirstName, a.middle_name as authorMiddleName,"
							+ " a.last_name as authorLastName, b.book_id as bookId, b.book_name as bookName, b.book_description as bookDescription,"
							+ " c.student_id as studentId, c.date_borrowed as dateBorrowed, c.date_returned as dateReturned"
							+ " from author a inner join book b on b.author_id = a.author_id"
							+ " left join borrowed_book c on c.book_id = b.book_id" + " where a.author_id = :authorId")
					.setParameter("authorId", authorId).unwrap(Query.class)
					.setTupleTransformer(new AuthorDTOResultTransformer()).getSingleResult();
			
			return authorDto;
		} catch (NoResultException e) {
			throw new AuthorNotFoundException("author ID: [" + authorId + "] not found");
		}

	}

}
