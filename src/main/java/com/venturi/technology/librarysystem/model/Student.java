package com.venturi.technology.librarysystem.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Student implements Serializable {
	
	private static final long serialVersionUID = 7001501734051331522L;

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private LocalDate birthDate;

}
