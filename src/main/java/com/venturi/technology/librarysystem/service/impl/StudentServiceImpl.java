package com.venturi.technology.librarysystem.service.impl;

import org.springframework.stereotype.Service;

import com.venturi.technology.librarysystem.entity.StudentEntity;
import com.venturi.technology.librarysystem.exception.StudentNotFoundException;
import com.venturi.technology.librarysystem.model.Student;
import com.venturi.technology.librarysystem.repository.StudentRepository;
import com.venturi.technology.librarysystem.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Override
	public Student getStudentById(Long studentId) {
		
		StudentEntity studentEntity = studentRepository.findById(studentId)
					.orElseThrow(() -> new StudentNotFoundException("Student ID: " + studentId + " not found."));
		
		Student student = new Student();
		student.setId(studentEntity.getStudentId());
		student.setFirstName(studentEntity.getFirstName());
		student.setMiddleName(studentEntity.getMiddleName());
		student.setLastName(studentEntity.getLastName());
		student.setEmail(studentEntity.getEmail());
		student.setBirthDate(studentEntity.getBirthDate());
		
		return student;
	}

}
