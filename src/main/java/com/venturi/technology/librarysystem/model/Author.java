package com.venturi.technology.librarysystem.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.venturi.technology.librarysystem.entity.AuthorEntity;

@JsonInclude(Include.NON_NULL)
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
	
	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + "]";
	}
	
}
