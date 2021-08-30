package com.safetynet.alerts;
import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonTest {
	@Autowired
	public MockMvc mockMvc;
	
	@Test
	public void testGetPersons() throws Exception {
		
		mockMvc.perform(get("/persons")).andExpect(status().isOk()).andExpect(jsonPath("$[4].firstName", is("Felicia")));
		
	}
	
	@Test
	public void testAddPerson() throws Exception {
		
		mockMvc.perform((( post("/person/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"firstName\": \"Tessa2\",\r\n"
						+ "    \"lastName\": \"Carman\",\r\n"
						+ "    \"address\": \"834 Binoc Ave\",\r\n"
						+ "    \"city\": \"Culver2\",\r\n"
						+ "    \"zip\": \"97451\",\r\n"
						+ "    \"phone\": \"841-874-6512\",\r\n"
						+ "    \"email\": \"tenz@email.com\"\r\n"
						+ "    ")
				.accept(MediaType.APPLICATION_JSON))));
		mockMvc.perform(get("/person/Tessa2")).andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("Tessa2")));
	}
}
