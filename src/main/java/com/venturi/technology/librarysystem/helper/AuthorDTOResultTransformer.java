package com.venturi.technology.librarysystem.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.query.TupleTransformer;

import com.venturi.technology.librarysystem.model.dto.AuthorDTO;
import com.venturi.technology.librarysystem.model.dto.BookDTO;

public class AuthorDTOResultTransformer implements TupleTransformer<AuthorDTO> {

	private Map<Integer, AuthorDTO> authorDtoMap = new LinkedHashMap<>();

	@Override
	public AuthorDTO transformTuple(Object[] tuple, String[] aliases) {

		AliasObjectMapper aliasObjectMapper = new AliasObjectMapper(tuple, aliases);

		Integer authorId = aliasObjectMapper.get("authorid");

		AuthorDTO authorDto = authorDtoMap.computeIfAbsent(authorId, id -> new AuthorDTO(aliasObjectMapper));
		
		authorDto.getBooks().add(new BookDTO(aliasObjectMapper));

		return authorDto;
	}
	
}
