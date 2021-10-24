package com.safetynet.alerts.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.MedicalrecordDetail;
import com.safetynet.alerts.model.Root;

/**
 * @author olvic
 *
 * create, read, update and delete medicalrecords
 */
@Service
public class MedicalrecordService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	public List<Medicalrecord> listMedicalrecords = new ArrayList<Medicalrecord>();
	
	/**
	 * @return List<Medicalrecord>
	 */
	public List<Medicalrecord> findAllMedicalrecords() {
		return listMedicalrecords;
	}
	
	/**
	 * @param medicalrecordDetail
	 * @return  Medicalrecord
	 */
	public Medicalrecord createmedicalrecord(MedicalrecordDetail medicalrecordDetail) {
		Medicalrecord medicalrecord = null;
		try {
			LOGGER.info("begin createMedicalrecord");
			medicalrecord = new Medicalrecord();			
			medicalrecord.setFirstName(medicalrecordDetail.getFirstName());
			medicalrecord.setLastName(medicalrecordDetail.getLastName());
			medicalrecord.setBirthdate(medicalrecordDetail.getBirthdate());
			medicalrecord.setMedications(medicalrecordDetail.getMedications());
			medicalrecord.setAllergies(medicalrecordDetail.getAllergies());
			listMedicalrecords.add(medicalrecord);
			return medicalrecord;
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end createMedicalrecord");
		}
		return null;
	}
	
	/**
	 * @param medicalrecordDetail
	 */
	public void updatemedicalrecord(MedicalrecordDetail medicalrecordDetail) {
		try {
			LOGGER.info("begin updateMedicalrecord");
			for (Medicalrecord medicalrecord : listMedicalrecords) {
				if (medicalrecord.getFirstName().equals(medicalrecordDetail.getFirstName()) && medicalrecord.getLastName().equals(medicalrecordDetail.getLastName())) {
					medicalrecord.setBirthdate(medicalrecordDetail.getBirthdate());
					medicalrecord.setMedications(medicalrecordDetail.getMedications());
					medicalrecord.setAllergies(medicalrecordDetail.getAllergies());
					
				}
			}
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end updateMedicalrecord");
		}
		
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @return Medicalrecord
	 */
	public Medicalrecord deletemedicalrecord(String firstName, String lastName) {
		try {
			LOGGER.info("begin deleteMedicalrecord");
			for (Medicalrecord medicalrecord : listMedicalrecords) {
				if (medicalrecord.firstName.equals(firstName) && medicalrecord.lastName.equals(lastName)) {
					listMedicalrecords.remove(medicalrecord);
					return medicalrecord;
				}

			}
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end deleteMedicalrecord");
		}
		return null;
	}
	
	/**
	 * @return List<Medicalrecord>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@PostConstruct
	public List<Medicalrecord> loadMedicalrecords() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Root root = mapper.readValue(new File("data.json"), Root.class);	
		listMedicalrecords = root.getMedicalrecords();
		return listMedicalrecords;
	}
}
