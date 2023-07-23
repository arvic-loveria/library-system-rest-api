package com.venturi.technology.librarysystem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.venturi.technology.librarysystem.entity.AuthorEntity;
import com.venturi.technology.librarysystem.model.dto.BookDTO;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class Author implements Serializable {

	private static final long serialVersionUID = 344818127121499450L;
	private Long authorId;
	private String firstName;
	private String middleName;	
	private String lastName;
	private List<Book> books;
	
	public Author() {}
	
	public Author(AuthorEntity authorEntity) {
		this.authorId = authorEntity.getAuthorId();
		this.firstName = authorEntity.getFirstName();
		this.middleName = authorEntity.getMiddleName();
		this.lastName = authorEntity.getLastName();
	}
	
	public void setBooks(List<BookDTO> bookDtos) {
		if (null == books) {
			this.books = new ArrayList<>();
		}
		
		bookDtos.stream().forEach(bookDto -> {
			Book book = new Book();
			book.setBookId(bookDto.getBookId().longValue());
			book.setBookName(bookDto.getBookName());
			book.setBookDescription(bookDto.getBookDescription());
			books.add(book);
		});
	}
	
}
