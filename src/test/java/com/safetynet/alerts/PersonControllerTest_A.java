package com.safetynet.alerts;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.Person;
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest_A {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	PersonController personController;
	
	@Test
	public void shouldReturnPersons() throws Exception {
		this.mockMvc.perform(get("/persons")).andExpect(status().isOk())
				.andExpect(jsonPath("$[4].firstName", is("Felicia")));
	}
	
	@Test
	public void shouldReturnPerson() throws Exception {
		this.mockMvc.perform(get("/person/Felicia")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is("Felicia")));
	}
	
	@Test
	public void shouldAddPerson() throws Exception {
		//GIVEN
		this.mockMvc.perform((( post("/person/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"firstName\": \"Tessa2\",\r\n"
						+ "    \"lastName\": \"Carman\",\r\n"
						+ "    \"address\": \"834 Binoc Ave\",\r\n"
						+ "    \"city\": \"Culver\",\r\n"
						+ "    \"zip\": \"97451\",\r\n"
						+ "    \"phone\": \"841-874-6512\",\r\n"
						+ "    \"email\": \"tenz@email.com\"\r\n"
						+ "    ")
				)));
		
		//WHEN
		Person expectedTessa2 = null;
		for(Person person: personController.listPersons) {
			if(person.getFirstName().equals("Tessa2")) expectedTessa2 = person;
		}
		
		//THEN
		assertThat(expectedTessa2 != null);
	}
	
	@Test
	public void shouldDeletePerson() throws Exception {
		//GIVEN
		this.mockMvc.perform(post("/person/delete/Tessa2/Carman"));
		
		//WHEN
		Person expectedTessa2 = null;
		for(Person person: personController.listPersons) {
			if(person.getFirstName().equals("Tessa2")) expectedTessa2 = person;
		}
		
		//THEN
		assertThat(expectedTessa2 == null);
	}
	
	@Test
	public void shouldUpdatePerson() throws Exception {
		//GIVEN
		this.mockMvc.perform((( post("/person/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"firstName\": \"Tessa\",\r\n"
						+ "    \"lastName\": \"Carman\",\r\n"
						+ "    \"address\": \"834 Binoc Ave\",\r\n"
						+ "    \"city\": \"Culver2\",\r\n"
						+ "    \"zip\": \"97451\",\r\n"
						+ "    \"phone\": \"841-874-6512\",\r\n"
						+ "    \"email\": \"tenz@email.com\"\r\n"
						+ "    ")
				)));
		
		//WHEN
		Person expectedUpdatedTessa = null;
		for(Person person: personController.listPersons) {
			if(person.getCity().equals("Culver2")) expectedUpdatedTessa = person;
		}
		
		//THEN
		assertThat(expectedUpdatedTessa != null);
	}
}
