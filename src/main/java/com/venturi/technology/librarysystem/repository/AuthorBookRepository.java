package com.venturi.technology.librarysystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venturi.technology.librarysystem.entity.AuthorBookEntity;

public interface AuthorBookRepository extends JpaRepository<AuthorBookEntity, Long> {

	Optional<List<AuthorBookEntity>> findByAuthorAuthorId(Long authorId);
}
