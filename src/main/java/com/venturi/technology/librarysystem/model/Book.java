package com.venturi.technology.librarysystem.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class Book implements Serializable {
	
	private static final long serialVersionUID = 6883031085960495876L;

	private Long bookId;
	private String bookName;
	private String bookDescription;

}
