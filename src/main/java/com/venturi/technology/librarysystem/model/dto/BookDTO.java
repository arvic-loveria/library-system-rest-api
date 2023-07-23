package com.venturi.technology.librarysystem.model.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.venturi.technology.librarysystem.helper.AliasObjectMapper;

import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class BookDTO {

	private Integer bookId;
	private String bookName;
	private String bookDescription;
	private Integer studentId;
	private Timestamp dateBorrowed;
	private Timestamp dateReturned;
	
	public BookDTO (AliasObjectMapper aliasIndexMapper) {
		this.bookId = aliasIndexMapper.get("bookid");
		this.bookName = aliasIndexMapper.get("bookname");
		this.bookDescription = aliasIndexMapper.get("bookdescription");
		this.studentId = aliasIndexMapper.get("studentid");
		this.dateBorrowed = aliasIndexMapper.get("dateborrowed");
		this.dateReturned = aliasIndexMapper.get("datereturned");
	}
	
	
}
