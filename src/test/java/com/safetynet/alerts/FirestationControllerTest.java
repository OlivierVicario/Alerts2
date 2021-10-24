package com.safetynet.alerts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.controller.FirestationController;
import com.safetynet.alerts.model.Firestation;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	public FirestationController firestationController;

	@Test
	public void shouldAddFirestation() throws Exception {
		// GIVEN
		this.mockMvc.perform(get("/firestation/add/19 Versailles street/25"));

		// WHEN
		Firestation expectedFirestation = null;
		for (Firestation firestation : firestationController.firestationService.listFirestations) {
			if (firestation.getAddress().equals("19 Versailles street"))
				expectedFirestation = firestation;
		}

		// THEN
		assertThat(expectedFirestation).isNotEqualTo(null);

	}

	@Test
	public void shouldUpdateFirestation() throws Exception {
		// GIVEN
		this.mockMvc.perform(put("/firestation/update/834 Binoc Ave/15"));//do nothing ?.................

		// WHEN
		Firestation expectedFirestation = null;
		for (Firestation firestation : firestationController.firestationService.listFirestations) {
			if (firestation.station.equals("15") && firestation.address.equals("834 Binoc Ave"))
				expectedFirestation = firestation;
		}

		// THEN
		assertThat(expectedFirestation).isNotNull();
	}

	@Test
	public void shouldDeleteFirestation() throws Exception {
		// GIVEN
		this.mockMvc.perform(get("/firestation/delete/25/21 Versailles street"));

		// WHEN
		Firestation expectedFirestation = null;
		for (Firestation firestation : firestationController.firestationService.listFirestations) {
			if (firestation.getStation().equals("25"))
				firestation.setAddress("21 Versailles street");
		}

		// THEN
		assertThat(expectedFirestation).isEqualTo(null);
	}
}
