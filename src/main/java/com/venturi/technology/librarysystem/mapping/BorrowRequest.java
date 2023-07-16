package com.venturi.technology.librarysystem.mapping;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class BorrowRequest implements Serializable {

	private static final long serialVersionUID = 3872406313859519564L;

	private Long studentId;	
	private List<Long> bookIds;
	
}
