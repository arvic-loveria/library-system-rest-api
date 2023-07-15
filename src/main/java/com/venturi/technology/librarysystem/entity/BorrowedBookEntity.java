package com.venturi.technology.librarysystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowed_book")
public class BorrowedBookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "borrowed_id")
	private Long borrowedId;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookEntity book;
	
	@Column(name = "date_borrowed")
	private LocalDateTime dateBorrowed;
	
	@Column(name = "date_returned")
	private LocalDateTime dateReturned;

	public Long getBorrowedId() {
		return borrowedId;
	}

	public void setBorrowedId(Long borrowedId) {
		this.borrowedId = borrowedId;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public LocalDateTime getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(LocalDateTime dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public LocalDateTime getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(LocalDateTime dateReturned) {
		this.dateReturned = dateReturned;
	}
	
}
