package com.venturi.technology.librarysystem.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venturi.technology.librarysystem.mapping.BorrowRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Test
	@Order(1)
	public void borrowAvailableBooksTest() throws Exception {
		
 		List<Long> bookIds = List.of(1L, 6L);
		BorrowRequest borrowRequest = new BorrowRequest();
		borrowRequest.setStudentId(1L);
		borrowRequest.setBookIds(bookIds);
		
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(borrowRequest);
		mockMvc.perform(post("/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.borrowed", hasSize(2)))
				.andExpect(jsonPath("$.notAvailable", hasSize(0)))
				.andExpect(jsonPath("$.borrowed[0].bookId", is(1)))
				.andExpect(jsonPath("$.borrowed[0].bookName", is("The Hunger Games")))
				.andExpect(jsonPath("$.borrowed[0].authors[0].authorId", is(1)))
				.andExpect(jsonPath("$.borrowed[0].authors[0].firstName", is("Suzanne")))
				.andExpect(jsonPath("$.borrowed[0].authors[0].lastName", is("Collins")))
				.andExpect(jsonPath("$.borrowed[1].bookId", is(6)))
				.andExpect(jsonPath("$.borrowed[1].bookName", is("Catching Fire")))
				.andExpect(jsonPath("$.borrowed[1].authors[0].authorId", is(1)))
				.andExpect(jsonPath("$.borrowed[1].authors[0].firstName", is("Suzanne")))
				.andExpect(jsonPath("$.borrowed[1].authors[0].lastName", is("Collins")));
		
	}
	
	@Test
	@Order(2)
	public void borrowNotAvailableBooksTest() throws Exception {
		
 		List<Long> bookIds = List.of(1L, 6L, 7L);
		BorrowRequest borrowRequest = new BorrowRequest();
		borrowRequest.setStudentId(2L);
		borrowRequest.setBookIds(bookIds);
		
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(borrowRequest);
		mockMvc.perform(post("/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.borrowed", hasSize(1)))
				.andExpect(jsonPath("$.notAvailable", hasSize(2)))
				.andExpect(jsonPath("$.borrowed[0].bookId", is(7)))
				.andExpect(jsonPath("$.borrowed[0].bookName", is("Gregor The Overlander")))
				.andExpect(jsonPath("$.borrowed[0].authors[0].authorId", is(1)))
				.andExpect(jsonPath("$.borrowed[0].authors[0].firstName", is("Suzanne")))
				.andExpect(jsonPath("$.borrowed[0].authors[0].lastName", is("Collins")))
				.andExpect(jsonPath("$.notAvailable[0].bookId", is(1)))
				.andExpect(jsonPath("$.notAvailable[0].bookName", is("The Hunger Games")))
				.andExpect(jsonPath("$.notAvailable[0].reason", is("Book is not yet returned.")))
				.andExpect(jsonPath("$.notAvailable[0].authors[0].authorId", is(1)))
				.andExpect(jsonPath("$.notAvailable[0].authors[0].firstName", is("Suzanne")))
				.andExpect(jsonPath("$.notAvailable[0].authors[0].lastName", is("Collins")))
				.andExpect(jsonPath("$.notAvailable[1].bookId", is(6)))
				.andExpect(jsonPath("$.notAvailable[1].bookName", is("Catching Fire")))
				.andExpect(jsonPath("$.notAvailable[1].reason", is("Book is not yet returned.")))
				.andExpect(jsonPath("$.notAvailable[1].authors[0].authorId", is(1)))
				.andExpect(jsonPath("$.notAvailable[1].authors[0].firstName", is("Suzanne")))
				.andExpect(jsonPath("$.notAvailable[1].authors[0].lastName", is("Collins")));
		
	}
	
}
