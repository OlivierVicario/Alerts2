package com.safetynet.alerts.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.MedicalrecordDetail;
import com.safetynet.alerts.model.Root;

import io.swagger.annotations.Api;

@Api(value = "MedicalrecordController", description = "REST APIs related to Medicalrecord Entity")
@RestController
public class MedicalrecordController {
	List<Medicalrecord> listMedicalrecords = new ArrayList<Medicalrecord>();
	
	// Find
	@GetMapping("/medicalrecords")
	List<Medicalrecord> findAllMedicalrecords() {
		return listMedicalrecords;
	}
	
	@PostMapping("/medicalrecord/add")
	public Medicalrecord createmedicalrecord(@RequestBody MedicalrecordDetail medicalrecordDetail) {
		Medicalrecord medicalrecord = new Medicalrecord();
		
		medicalrecord.setFirstName(medicalrecordDetail.getFirstName());
		medicalrecord.setLastName(medicalrecordDetail.getLastName());
		medicalrecord.setBirthdate(medicalrecordDetail.getBirthdate());
		medicalrecord.setMedications(medicalrecordDetail.getMedications());
		medicalrecord.setAllergies(medicalrecordDetail.getAllergies());
		listMedicalrecords.add(medicalrecord);
		return medicalrecord;
	}
	
	
	@PutMapping("/medicalrecord/update/{firstName}/{lastName}/{address}/{city}/{zip}/{phone}/{email}")
	public Medicalrecord updatemedicalrecord(@RequestBody MedicalrecordDetail medicalrecordDetail) {
		for (Medicalrecord medicalrecord : listMedicalrecords) {
			if (medicalrecord.getFirstName().equals(medicalrecordDetail.getFirstName()) && medicalrecord.getLastName().equals(medicalrecordDetail.getLastName())) {
				medicalrecord.setBirthdate(medicalrecordDetail.getBirthdate());
				medicalrecord.setMedications(medicalrecordDetail.getMedications());
				medicalrecord.setAllergies(medicalrecordDetail.getAllergies());
				return medicalrecord;
			}
		}
		return null;
	}

	@DeleteMapping("/medicalrecord/delete/{firstName}/{lastName}")
	public Medicalrecord deletemedicalrecord(@PathVariable String firstName, @PathVariable String lastName) {
		System.out.println("appel delete");
		for (Medicalrecord medicalrecord : listMedicalrecords) {
			if (medicalrecord.firstName.equals(firstName) && medicalrecord.lastName.equals(lastName)) {
				listMedicalrecords.remove(medicalrecord);
				return medicalrecord;
			}

		}
		return null;
	}
	
	
	@PostConstruct
	public void loadData() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		// lecture fichier local
		Root root = mapper.readValue(new File("data.json"), Root.class);

		// lecture fichier distant
		/*
		 * URL distantInputDataURL = new URL(
		 * "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"
		 * ); Root root = mapper.readValue(distantInputDataURL.openStream(),
		 * Root.class);
		 */

		listMedicalrecords = root.getMedicalrecords();
	}

}
