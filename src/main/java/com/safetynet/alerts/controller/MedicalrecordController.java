package com.safetynet.alerts.controller;

import java.util.List;

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

import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.MedicalrecordDetail;
import com.safetynet.alerts.service.MedicalrecordService;

import io.swagger.annotations.Api;

@Api(value = "MedicalrecordController", description = "REST APIs related to Medicalrecord Entity")
@RestController
public class MedicalrecordController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	public MedicalrecordService medicalrecordService;

	@GetMapping("/medicalrecords")
	List<Medicalrecord> findAllMedicalrecords() {
		return medicalrecordService.listMedicalrecords;
	}
	
	@PostMapping("/medicalrecord/add")
	public Medicalrecord createmedicalrecord(@RequestBody MedicalrecordDetail medicalrecordDetail) {
		try {
			LOGGER.info("begin createMedicalrecord");
			return medicalrecordService.createmedicalrecord(medicalrecordDetail);
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end createMedicalrecord");
		}
		return null;
	}
	
	
	@PutMapping("/medicalrecord/update")
	public void updatemedicalrecord(@RequestBody MedicalrecordDetail medicalrecordDetail) {
		try {
			LOGGER.info("begin updateMedicalrecord");
medicalrecordService.updatemedicalrecord(medicalrecordDetail);
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end updateMedicalrecord");
		}
		
	}

	@DeleteMapping("/medicalrecord/delete/{firstName}/{lastName}")
	public Medicalrecord deletemedicalrecord(@PathVariable String firstName, @PathVariable String lastName) {
		try {
			LOGGER.info("begin deleteMedicalrecord");
			medicalrecordService.deletemedicalrecord(firstName, lastName);
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end deleteMedicalrecord");
		}
		return null;
	}
	

}
