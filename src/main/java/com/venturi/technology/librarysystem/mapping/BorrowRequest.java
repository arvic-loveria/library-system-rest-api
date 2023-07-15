package com.venturi.technology.librarysystem.mapping;

import java.io.Serializable;
import java.util.List;

public class BorrowRequest implements Serializable {

	private static final long serialVersionUID = 3872406313859519564L;

	private Long studentId;	
	private List<Long> bookIds;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}

	@Override
	public String toString() {
		return "BorrowRequest [studentId=" + studentId + ", bookIds=" + bookIds + "]";
	}
	
}
