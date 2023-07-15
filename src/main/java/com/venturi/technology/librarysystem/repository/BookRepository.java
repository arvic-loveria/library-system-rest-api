package com.venturi.technology.librarysystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venturi.technology.librarysystem.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	Optional<BookEntity> findByAuthorBookAuthorAuthorId(Long authorId);
	
}
