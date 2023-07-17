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
public class BookAuthor extends Book implements Serializable {
	
	private static final long serialVersionUID = 3692489973718573209L;
	private List<Author> authors;
	
}
