package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.MedicalrecordDetail;
import com.safetynet.alerts.service.MedicalrecordService;

import io.swagger.annotations.Api;

@Api(value = "MedicalrecordController", description = "REST APIs related to Medicalrecord Entity")
@RestController
public class MedicalrecordController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	public List<Medicalrecord> listMedicalrecords = new ArrayList<Medicalrecord>();
	@Autowired
	MedicalrecordService medicalrecordService;
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
	
	
	@PutMapping("/medicalrecord/update")
	public void updatemedicalrecord(@RequestBody MedicalrecordDetail medicalrecordDetail) {
		for (Medicalrecord medicalrecord : listMedicalrecords) {
			if (medicalrecord.getFirstName().equals(medicalrecordDetail.getFirstName()) && medicalrecord.getLastName().equals(medicalrecordDetail.getLastName())) {
				medicalrecord.setBirthdate(medicalrecordDetail.getBirthdate());
				medicalrecord.setMedications(medicalrecordDetail.getMedications());
				medicalrecord.setAllergies(medicalrecordDetail.getAllergies());
				
			}
		}
		
	}

	@DeleteMapping("/medicalrecord/delete/{firstName}/{lastName}")
	public Medicalrecord deletemedicalrecord(@PathVariable String firstName, @PathVariable String lastName) {
		for (Medicalrecord medicalrecord : listMedicalrecords) {
			if (medicalrecord.firstName.equals(firstName) && medicalrecord.lastName.equals(lastName)) {
				listMedicalrecords.remove(medicalrecord);
				return medicalrecord;
			}

		}
		return null;
	}
	
	
	@PostConstruct
	public void loadData() {
		try {
			LOGGER.info("begin MedicalrecordController.loadData");
			listMedicalrecords = medicalrecordService.loadMedicalrecords();

		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end MedicalrecordController.loadData");
		}
	}

}
