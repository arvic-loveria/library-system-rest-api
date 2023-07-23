package com.venturi.technology.librarysystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "author_book")
public class AuthorBookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_book_id")
	private Long authorBookId;
		
	@ManyToOne
	@JoinColumn(name = "author_id")
	private AuthorEntity author;

	public Long getAuthorBookId() {
		return authorBookId;
	}

	public void setAuthorBookId(Long authorBookId) {
		this.authorBookId = authorBookId;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}
	
}
