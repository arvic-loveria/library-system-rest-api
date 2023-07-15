package com.venturi.technology.librarysystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venturi.technology.librarysystem.model.Student;
import com.venturi.technology.librarysystem.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long studentId){
		
		return ResponseEntity.ok(studentService.getStudentById(studentId));
	}
}
