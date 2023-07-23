package com.venturi.technology.librarysystem.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.venturi.technology.librarysystem.helper.AliasObjectMapper;

import lombok.Getter;

@Getter
public class AuthorDTO {
	
	private Integer authorId;
	private String authorFirstName;
	private String authorMiddleName;
	private String authorLastName;
	private List<BookDTO> books = new ArrayList<>();
	
	public AuthorDTO(AliasObjectMapper aliasIndexMapper) {
		
		this.authorId =  aliasIndexMapper.get("authorid");
		this.authorFirstName = aliasIndexMapper.get("authorfirstname");
		this.authorMiddleName = aliasIndexMapper.get("authormiddlename");
		this.authorLastName = aliasIndexMapper.get("authorlastname");
		
	}
	

}
