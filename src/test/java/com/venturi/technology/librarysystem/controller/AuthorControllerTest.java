package com.venturi.technology.librarysystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getBooksByAuthorIdTest() throws Exception {
		
		mockMvc.perform(get("/authors/3/books"))
			   	.andExpect(status().isOk())
			   	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   	.andExpect(jsonPath("$", hasSize(1)))
			   	.andExpect(jsonPath("$.[0].bookId", is(3)))
				.andExpect(jsonPath("$.[0].bookName", is("Pride and Prejudice")))
				.andExpect(jsonPath("$.[0].author.authorId", is(3)))
				.andExpect(jsonPath("$.[0].author.firstName", is("Jane")))
				.andExpect(jsonPath("$.[0].author.lastName", is("Austen")));
		
	}
	
}
