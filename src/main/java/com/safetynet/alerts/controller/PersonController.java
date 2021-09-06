package com.safetynet.alerts.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonDetail;
import com.safetynet.alerts.model.Root;

import io.swagger.annotations.Api;

@Api(value = "PersonController", description = "REST APIs related to Person Entity")
@RestController
public class PersonController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	public List<Person> listPersons = new ArrayList<Person>();

	// Find
	@GetMapping("/persons")
	List<Person> findAllPersonnes() {
		LOGGER.info("begin findPersons");
		LOGGER.info("end findPersons");
		return listPersons;
		
	}

	@GetMapping("/person/{firstName}")
	Person findPersonneByFirstName(@PathVariable String firstName) {
		for (Person person : listPersons) {
			if (person.getFirstName().equals(firstName)) {
				return person;
			}
		}
		return null;
	}
	
	@PostMapping("/person/add")
	public Person createPerson(@RequestBody PersonDetail personDetail) {
		Person person = new Person();
		person.setAddress(personDetail.getAdress());
		person.setFirstName(personDetail.getFirstName());
		person.setLastName(personDetail.getLastName());
		person.setCity(personDetail.getCity());
		person.setZip(personDetail.getZipCode());
		person.setPhone(personDetail.getPhone());
		person.setEmail(personDetail.getEmail());
		listPersons.add(person);
		return person;
	}

	@DeleteMapping("/person/delete/{firstName}/{lastName}")
	public Person deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
		System.out.println("appel delete");
		for (Person person : listPersons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				listPersons.remove(person);
				return person;
			}

		}
		return null;
	}

	@PutMapping("/person/update/{firstName}/{lastName}/{address}/{city}/{zip}/{phone}/{email}")
	public Person updatePerson(@PathVariable String firstName, @PathVariable String lastName,
			@PathVariable String address, @PathVariable String city, @PathVariable String zip,
			@PathVariable String phone, @PathVariable String email) {
		for (Person person : listPersons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				person.setAddress(address);
				person.setCity(city);
				person.setZip(zip);
				person.setPhone(phone);
				person.setEmail(email);
				return person;
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

		listPersons = root.getPersons();
	}

}
