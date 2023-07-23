package com.venturi.technology.librarysystem.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.venturi.technology.librarysystem.helper.AliasObjectMapper;

import lombok.Getter;

@Getter
public class BookAndBorrowedBookDTO {

	private Integer bookId;
	private String bookName;
	private String bookDescription;
	private List<BorrowedBookDTO> borrowedBooks = new ArrayList<>();
	
	public BookAndBorrowedBookDTO(AliasObjectMapper aliasObjectMapper) {
		this.bookId = aliasObjectMapper.get("bookid");
		this.bookName = aliasObjectMapper.get("bookname");
		this.bookDescription = aliasObjectMapper.get("bookdescription");
	}
}
