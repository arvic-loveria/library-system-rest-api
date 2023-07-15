package com.venturi.technology.librarysystem.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookAuthor extends Book implements Serializable {
	
	private static final long serialVersionUID = 3692489973718573209L;
	private Author author;

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BookAuthor [author=" + author + "]";
	}
	
}
