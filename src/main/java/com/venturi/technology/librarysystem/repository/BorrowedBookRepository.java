package com.venturi.technology.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venturi.technology.librarysystem.entity.BorrowedBookEntity;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBookEntity, Long> {
	
}
