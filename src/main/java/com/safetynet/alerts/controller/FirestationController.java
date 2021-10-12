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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationService;

import io.swagger.annotations.Api;

@Api(value = "FirestationController", description = "REST APIs related to Firestation Entity")
@RestController
public class FirestationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FirestationController.class);
	
	
	public List<Firestation> listFirestations = new ArrayList<Firestation>();
	@Autowired
	FirestationService firestationService;
	
	
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
	
	
	@DeleteMapping("/firestation/delete/address/{address}")
	public Firestation deleteFirestationByAddress(@PathVariable String address) {
		for (Firestation firestation : listFirestations) {
			if (firestation.address.equals(address)) {
				listFirestations.remove(firestation);
				return firestation;
			}

		}
		return null;
	}
	
	@DeleteMapping("/firestation/delete/station/{station}")
	public Firestation deleteFirestationByStation(@PathVariable String station) {
		for (Firestation firestation : listFirestations) {
			if (firestation.station.equals(station)) {
				listFirestations.remove(firestation);
				return firestation;
			}

		}
		return null;
	}
	
	
	@PutMapping("/firestation/update/{address}/{station}")
	public Firestation updateFirestation(@PathVariable String address, @PathVariable String station)
	{
		for (Firestation firestation : listFirestations) {
			if (firestation.address.equals(address)) {
				firestation.setStation(station);
				return firestation;
			}
		}
		return null;
		
	}
	
	@PostConstruct
	public void loadData() {
		try {
			LOGGER.info("begin FirestationController.loadData");
			listFirestations = firestationService.loadFirestations();

		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end FirestationController.loadData");
		}
	}
}
