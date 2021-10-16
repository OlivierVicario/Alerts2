package com.safetynet.alerts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class PersonController_0_Test {
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
		// GIVEN
		this.mockMvc.perform(((post("/person/add").contentType(MediaType.APPLICATION_JSON)
		.content("{\"firstName\":\"Tessa2\",\"lastName\":\"Carman\",\"address\":\"834 Binoc Ave\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"tenz@email.com\"}"))));
		// WHEN
		Person expectedPerson = null;
		for (Person person : personController.listPersons) {
			if (person.getFirstName().equals("Tessa2"))
				expectedPerson = person;
		}

		// THEN
		assertThat(expectedPerson).isNotNull();
	}

	@Test
	public void shouldDeletePerson() throws Exception {
		// GIVEN
		this.mockMvc.perform(delete("/person/delete/Tessa/Carman"));

		// WHEN
		Person expectedPerson = null;
		for (Person person : personController.listPersons) {
			if (person.getFirstName().equals("Tessa") && person.getLastName().equals("Carman"))
				expectedPerson = person;
		}

		// THEN
		assertThat(expectedPerson).isNull();
	}

	@Test
	public void shouldUpdatePerson() throws Exception {
		// GIVEN
		this.mockMvc
				.perform(put("/person/update/Tessa/Carman/834 Binoc Ave/Culver2/97451/841-874-6512/tenz@email.com"));

		// WHEN
		Person expectedUpdatedPerson = null;
		for (Person person : personController.listPersons) {
			if (person.getCity().equals("Culver2"))
				expectedUpdatedPerson = person;
		}

		// THEN
		assertThat(expectedUpdatedPerson).isNotNull();
	}
}
