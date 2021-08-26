package com.safetynet.alerts.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Root;

@RestController
public class FirestationController {
	
	List<Firestation> listFirestations = new ArrayList<Firestation>();
	
	// Find
	@GetMapping("/firestations")
	List<Firestation> findAllFirestations() {
		return listFirestations;
	}

	@GetMapping("/firestation/add/{address}/{station}")
	public Firestation createfirestation(@PathVariable String address, @PathVariable String station) {
		Firestation firestation = new Firestation();
		firestation.setAddress(address);
		firestation.setStation(station);
		listFirestations.add(firestation);
		return firestation;
	}
	
	
	@DeleteMapping("/person/delete/address/{address}")
	public Firestation deleteFirestation(@PathVariable String address) {
		System.out.println("appel delete");
		for (Firestation firestation : listFirestations) {
			if (firestation.address.equals(address)) {
				listFirestations.remove(firestation);
				return firestation;
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

		listFirestations = root.getFirestations();
	}
}
