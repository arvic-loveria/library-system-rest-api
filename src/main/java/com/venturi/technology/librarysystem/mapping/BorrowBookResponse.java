package com.venturi.technology.librarysystem.mapping;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.venturi.technology.librarysystem.model.Book;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class BorrowBookResponse extends Book implements Serializable {

	private static final long serialVersionUID = -4701215316107484548L;

	private String reason;
	
}
