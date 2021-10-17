package com.safetynet.alerts;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonDetail;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonController_1_Test {
	//variante test avec fourniture infos mock dans le code
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PersonController personController;

	@Test
	public void shouldReturnPersons() throws Exception {
		// GIVEN
		List<Person> listPerson = new ArrayList<>();
		Person person = new Person();
		person.setFirstName("testNom");
		person.setLastName("testLastName");
		person.setAddress("testAdress");
		person.setCity("testCity");
		person.setEmail("email");
		person.setPhone("phone");
		person.setEmail("email");
		listPerson.add(person);

		when(personController.findAllPersons()).thenReturn(listPerson);
		this.mockMvc.perform(get("/persons")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("testNom")));
	}

	@Test
	public void shouldReturnPerson() throws Exception {
		// GIVEN
		Person person = new Person();
		person.setFirstName("testNom");
		person.setLastName("testLastName");
		person.setAddress("testAdress");
		person.setCity("testCity");
		person.setEmail("email");
		person.setPhone("phone");
		person.setEmail("email");

		when(personController.findPersonneByFirstName("testNom")).thenReturn(person);

		this.mockMvc.perform(get("/person/testNom")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is("testNom")));
	}

	@Test
	public void shouldAddPerson() throws Exception {
		// GIVEN
		PersonDetail personDetailToAdd = new PersonDetail();
		personDetailToAdd.setFirstName("nomToADD");
		personDetailToAdd.setLastName("lastNameToADD");
		personDetailToAdd.setAdress("adress");
		personDetailToAdd.setCity("cityToADD");
		personDetailToAdd.setEmail("emailToADD");
		personDetailToAdd.setPhone("phoneToADD");
		Person person = new Person();
		person.setFirstName("nomToADD");
		person.setLastName("lastNameToADD");
		person.setAddress("adressToADD");
		person.setCity("cityToADD");
		person.setEmail("emailToADD");
		person.setPhone("phoneToADD");

		when(personController.createPerson(any(PersonDetail.class))).thenReturn(person);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(post("/person/add").content(objectMapper.writeValueAsString(personDetailToAdd))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").exists()).andExpect(jsonPath("$.firstName", is("nomToADD")));
	}

	@Test
	public void shouldDeletePerson() throws Exception {
		// GIVEN
		PersonDetail personDetailToAdd = new PersonDetail();
		personDetailToAdd.setFirstName("nomToADD");
		personDetailToAdd.setLastName("lastNameToADD");
		personDetailToAdd.setAdress("adress");
		personDetailToAdd.setCity("cityToADD");
		personDetailToAdd.setEmail("emailToADD");
		personDetailToAdd.setPhone("phoneToADD");
		Person person = new Person();
		person.setFirstName("nomToADD");
		person.setLastName("lastNameToADD");
		person.setAddress("adressToADD");
		person.setCity("cityToADD");
		person.setEmail("emailToADD");
		person.setPhone("phoneToADD");

		when(personController.createPerson(any(PersonDetail.class))).thenReturn(person);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(post("/person/add").content(objectMapper.writeValueAsString(personDetailToAdd)));

		mockMvc.perform(delete("/person/delete/nomToADD/lastNameToADD")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").doesNotExist());
	}

	@Test
	public void shouldUpdatePerson() throws Exception {
		// GIVEN
		PersonDetail personDetailToAdd = new PersonDetail();
		personDetailToAdd.setFirstName("nomToADD");
		personDetailToAdd.setLastName("lastNameToADD");
		personDetailToAdd.setAdress("adressToADD");
		personDetailToAdd.setCity("cityToADD");
		personDetailToAdd.setZipCode("zipToADD");
		personDetailToAdd.setEmail("emailToADD");
		personDetailToAdd.setPhone("phoneToADD");
		Person person = new Person();
		person.setFirstName("nomToADD");
		person.setLastName("lastNameToADD");
		person.setAddress("adressToADD");
		person.setCity("cityToADD");
		person.setZip("zipToADD");
		person.setEmail("emailToADD");
		person.setPhone("phoneToADD");

		/*when(personController.createPerson(any(PersonDetail.class))).thenReturn(person);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(post("/person/add").content(objectMapper.writeValueAsString(personDetailToAdd))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));*/
		
		when(personController.updatePerson("nomToADD", "lastNameToADD", "adressToADD", "cityToADD", "zipToADD", "emailToADD", "phoneToADD")).thenReturn(person);
		mockMvc.perform(put("/person/update/nomToADD/lastNameToADD/adressToADD/cityToADD/zipToADD/emailToADD/phoneToADD"))
		
				.andExpect(status().isOk()).andExpect(jsonPath("$.city").exists())
				.andExpect(jsonPath("$.city", is("cityToADD")));

	}

}
