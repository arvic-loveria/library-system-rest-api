package com.venturi.technology.librarysystem.model.dto;

import java.sql.Timestamp;

import com.venturi.technology.librarysystem.helper.AliasObjectMapper;

import lombok.Getter;

@Getter
public class BorrowedBookDTO {
	
	private Integer studentId;
	private Timestamp dateBorrowed;
	private Timestamp dateReturned;
	
	public BorrowedBookDTO(AliasObjectMapper aliasObjectMapper) {
		this.studentId = aliasObjectMapper.get("studentid");
		this.dateBorrowed = aliasObjectMapper.get("dateborrowed");
		this.dateReturned = aliasObjectMapper.get("datereturned");
	}
	
}

