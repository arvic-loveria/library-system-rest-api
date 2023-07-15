package com.venturi.technology.librarysystem.mapping;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.venturi.technology.librarysystem.model.BookAuthor;

@JsonInclude(Include.NON_NULL)
public class BorrowBookResponse extends BookAuthor implements Serializable {

	private static final long serialVersionUID = -4701215316107484548L;

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
