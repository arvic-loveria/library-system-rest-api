package com.venturi.technology.librarysystem.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.query.TupleTransformer;

import com.venturi.technology.librarysystem.model.dto.BookAndBorrowedBookDTO;
import com.venturi.technology.librarysystem.model.dto.BorrowedBookDTO;

public class BookAndBorrowedBookResultTransformer implements TupleTransformer<BookAndBorrowedBookDTO> {

	private Map<Integer, BookAndBorrowedBookDTO> bookAndBorrowBookMap = new LinkedHashMap<>();
	
	@Override
	public BookAndBorrowedBookDTO transformTuple(Object[] tuple, String[] aliases) {
		
		AliasObjectMapper aliasObjectMapper = new AliasObjectMapper(tuple, aliases);
		
		Integer bookId = aliasObjectMapper.get("bookid");
		
		BookAndBorrowedBookDTO bookAndBorrowedBookDTO = bookAndBorrowBookMap.computeIfAbsent(bookId, id -> new BookAndBorrowedBookDTO(aliasObjectMapper));
		
		bookAndBorrowedBookDTO.getBorrowedBooks().add(new BorrowedBookDTO(aliasObjectMapper));
		
		return bookAndBorrowedBookDTO;
	}

}
