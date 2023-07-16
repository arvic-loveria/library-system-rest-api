package com.venturi.technology.librarysystem.model;

import java.io.Serializable;
import java.util.List;

import com.venturi.technology.librarysystem.mapping.BorrowBookResponse;

import lombok.Data;

@Data
public class BorrowResponse implements Serializable {

	private static final long serialVersionUID = 6876569343961337532L;

	private List<BorrowBookResponse> borrowed;
	private List<BorrowBookResponse> notAvailable;

	public void setNotAvailable(List<BorrowBookResponse> notAvailable) {
		this.notAvailable = notAvailable;
	}
	
}
