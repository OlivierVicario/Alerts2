package com.safetynet.alerts.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Root;
@Service
public class MedicalrecordService {
	public List<Medicalrecord> loadMedicalrecords() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		// lecture fichier local
		Root root = mapper.readValue(new File("data.json"), Root.class);
		
		 //lecture fichier distant

		/*URL distantInputDataURL = new URL(
				"https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
		root = mapper.readValue(distantInputDataURL.openStream(), Root.class);*/
		
		return root.getMedicalrecords();
	}
}
