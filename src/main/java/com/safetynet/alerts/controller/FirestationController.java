package com.safetynet.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationService;

import io.swagger.annotations.Api;

@Api(value = "FirestationController", description = "REST APIs related to Firestation Entity")
@RestController
public class FirestationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FirestationController.class);
	

	@Autowired
	public FirestationService firestationService;
	
	

	@GetMapping("/firestations")
	public List<Firestation> findAllFirestations() {
		return firestationService.findAllFirestations();
	}

	@GetMapping("/firestation/add/{address}/{station}")
	public Firestation createfirestation(@PathVariable String address, @PathVariable String station) {
		try {
			LOGGER.info("begin createFirestation");
			return firestationService.createfirestation(address, station);
		 } catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end createFirestation");
		}
		return null;
	}
	
	
	@DeleteMapping("/firestation/delete/address/{address}")
	public Firestation deleteFirestationByAddress(@PathVariable String address) {
		try {
			LOGGER.info("end deleteFirestation");
			return firestationService.deleteFirestationByAddress(address);
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end deleteFirestation");
		}
		return null;
	}
	
	@DeleteMapping("/firestation/delete/station/{station}")
	public Firestation deleteFirestationByStation(@PathVariable String station) {
		try {
			LOGGER.info("begin deleteFirestation");
			return firestationService.deleteFirestationByStation(station);
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end deleteFirestation");
		}
		return null;
	}
	
	
	@PutMapping("/firestation/update/{address}/{station}")
	public Firestation updateFirestation(@PathVariable String address, @PathVariable String station)
	{
		try {
			LOGGER.info("begin updateFirestation");
			return firestationService.updateFirestation(address, station);
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end updateFirestation");
		}
		return null;
	}
}
