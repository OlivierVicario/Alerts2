package com.safetynet.alerts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.controller.MedicalrecordController;
import com.safetynet.alerts.model.Medicalrecord;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalrecordControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	MedicalrecordController medicalrecordController;

	@Test
	public void shouldAddMedicalrecord() throws Exception {
		// GIVEN
		this.mockMvc.perform(((post("/medicalrecord/add").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Tessa2\",\"lastName\":\"Carman\",\"birthdate\":\"0/0/0\",\"medications\":[\"Culver: 2mg\"],\"allergies\":[\"water\",\"soap\"]}"))));

		// WHEN
		Medicalrecord expectedMedicalrecord = null;
		for (Medicalrecord medicalrecord : medicalrecordController.medicalrecordService.listMedicalrecords) {
			if (medicalrecord.getFirstName().equals("Tessa2"))
				expectedMedicalrecord = medicalrecord;
		}

		// THEN
		assertThat(expectedMedicalrecord).isNotNull();
	}

	@Test
	public void shouldUpdateMedicalrecord() throws Exception {
		// GIVEN
		this.mockMvc.perform(put("/medicalrecord/update").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1988\",\"medications\":[\"Culver: 2mg\"],\"allergies\":[\"water\",\"soap\"]}"));

		// WHEN
		Medicalrecord expectedMedicalrecord = null;
		for (Medicalrecord medicalrecord : medicalrecordController.medicalrecordService.listMedicalrecords) {
			if (medicalrecord.getBirthdate().equals("03/06/1988"))
				expectedMedicalrecord = medicalrecord;
		}

		// THEN
		assertThat(expectedMedicalrecord).isNotNull();
	}

	@Test
	public void shouldDeleteMedicalrecord() throws Exception {
		// GIVEN
		this.mockMvc.perform(delete("/medicalrecord/delete/Tessa2/Carman"));

		// WHEN
		Medicalrecord expectedMedicalrecord = null;
		for (Medicalrecord medicalrecord : medicalrecordController.medicalrecordService.listMedicalrecords) {
			if (medicalrecord.getFirstName().equals("Tessa2") && medicalrecord.getLastName().equals("Carman"))
				expectedMedicalrecord = medicalrecord;
		}

		// THEN
		assertThat(expectedMedicalrecord).isNull();
	}
}
