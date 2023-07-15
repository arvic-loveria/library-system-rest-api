package com.venturi.technology.librarysystem.model;

import java.io.Serializable;
import java.util.List;

import com.venturi.technology.librarysystem.mapping.BorrowBookResponse;

public class BorrowResponse implements Serializable {

	private static final long serialVersionUID = 6876569343961337532L;

	private List<BorrowBookResponse> borrowed;
	private List<BorrowBookResponse> notAvailable;

	public List<BorrowBookResponse> getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(List<BorrowBookResponse> borrowed) {
		this.borrowed = borrowed;
	}

	public List<BorrowBookResponse> getNotAvailable() {
		return notAvailable;
	}

	public void setNotAvailable(List<BorrowBookResponse> notAvailable) {
		this.notAvailable = notAvailable;
	}
	
}
