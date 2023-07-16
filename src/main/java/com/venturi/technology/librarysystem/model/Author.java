package com.venturi.technology.librarysystem.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.venturi.technology.librarysystem.entity.AuthorEntity;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class Author implements Serializable {

	private static final long serialVersionUID = 344818127121499450L;
	private Long authorId;
	private String firstName;
	private String middleName;	
	private String lastName;

	public Author() {}
	
	public Author(AuthorEntity authorEntity) {
		this.authorId = authorEntity.getAuthorId();
		this.firstName = authorEntity.getFirstName();
		this.middleName = authorEntity.getMiddleName();
		this.lastName = authorEntity.getLastName();
	}
	
}
