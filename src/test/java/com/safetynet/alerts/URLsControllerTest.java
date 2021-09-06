package com.safetynet.alerts;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.controller.URLsController;
@SpringBootTest
@AutoConfigureMockMvc
public class URLsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	URLsController urlscontroller;

	@Test
	void getPersonsbyFirestationTest() throws Exception {		
		this.mockMvc.perform(get("/firestation?stationNumber=4"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.listResidents[0].lastName", is("Cooper")));	
	}
	
	@Test
	void getChildrensbyAddressTest() throws Exception {		
		this.mockMvc.perform(get("/childAlert?address=1509 Culver St"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.[0].lastName", is("Boyd")));	
	}
	
	@Test
	void getPhonesByFireStationTest() throws Exception {		
		this.mockMvc.perform(get("/phoneAlert?firestation=3"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.[0]", is("841-874-6512")));	
	}
	
	@Test
	void getPatientsByFireStationTest() throws Exception {		
		this.mockMvc.perform(get("/fire?stationNumber=4"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.[0].firstName", is("Tony")));	
	}
	
	@Test
	void getHomesByFireStationTest() throws Exception {		
		this.mockMvc.perform(get("/flood/stations?stationNumber=4"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.[0].address", is("489 Manchester St")));	
	}
	
	@Test
	void getPersonInfosByNameTest() throws Exception {		
		this.mockMvc.perform(get("/personInfo?firstName=Allison&lastName=Boyd"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.[0].age", is(55)));	
	}
	
	@Test
	void getEmailsByCityTest() throws Exception {		
		this.mockMvc.perform(get("/communityEmail?city=Culver"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.[5]", is("drk@email.com")));	
	}
}
