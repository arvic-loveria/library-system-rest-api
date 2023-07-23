package com.venturi.technology.librarysystem.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class Book implements Serializable {
	
	private static final long serialVersionUID = 6883031085960495876L;

	private Long bookId;
	private String bookName;
	private String bookDescription;
	List<Author> authors;

}
