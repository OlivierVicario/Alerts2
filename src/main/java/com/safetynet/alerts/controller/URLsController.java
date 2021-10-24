package com.safetynet.alerts.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.output.Child;
import com.safetynet.alerts.model.output.Home;
import com.safetynet.alerts.model.output.Patient;
import com.safetynet.alerts.model.output.PersonInfo;
import com.safetynet.alerts.model.output.ResidentRepport;
import com.safetynet.alerts.service.URLsService;;

@RestController
public class URLsController {
	
	@Autowired
	public URLsService urlsService;
	
	@GetMapping("/firestation")
	public
	ResidentRepport findResidentsByFirestation(@RequestParam(value = "stationNumber") String station) throws ParseException {
			
		return urlsService.findResidentsByFirestation(station);
	}

	@GetMapping("/childAlert")
	ArrayList<Child> findChildrenByAddress(@RequestParam(value = "address") String address) {

		return urlsService.findChildrenByAddress(address);
	}

	@GetMapping("/phoneAlert")
	ArrayList<String> findPhonesByFireStation(@RequestParam(value = "firestation") String station) {
		
		return urlsService.findPhonesByFireStation(station);
	}

	@GetMapping("/fire")
	List<Patient> findPatientsByFirestation(@RequestParam(value = "stationNumber") String station)
			throws ParseException {
		
		return urlsService.findPatientsByFirestation(station);
	}

	@GetMapping("/flood/stations")
	List<Home> findHomesByFirestation(@RequestParam(value = "stationNumber") String[] stations)
			throws ParseException {

		return urlsService.findHomesByFirestation(stations);
	}

	
	@GetMapping("/personInfo")
	List<PersonInfo> findPersonInfosByNames(@RequestParam(value = "firstName") String firstName,@RequestParam(value = "lastName") String lastName)
			throws ParseException {
		return urlsService.findPersonInfosByNames(firstName, lastName);
	}
	
	
	@GetMapping("/communityEmail")
	List<String> findEmailsByCity(@RequestParam(value="city") String city){
		
		return urlsService.findEmailsByCity(city);		
	}
	
}
