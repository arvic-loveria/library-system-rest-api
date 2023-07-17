package com.venturi.technology.librarysystem.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "book_description")
	private String bookDescription;
		
	@OneToMany(mappedBy = "book")
	private Set<AuthorBookEntity> authorBooks;
	
	@OneToMany(mappedBy = "book")
	private Set<BorrowedBookEntity> borrowedBooks;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public Set<AuthorBookEntity> getAuthorBooks() {
		return authorBooks;
	}

	public void setAuthorBooks(Set<AuthorBookEntity> authorBooks) {
		this.authorBooks = authorBooks;
	}

	public Set<BorrowedBookEntity> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(Set<BorrowedBookEntity> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

}
