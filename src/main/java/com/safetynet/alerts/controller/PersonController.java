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
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonDetail;
import com.safetynet.alerts.service.PersonService;

import io.swagger.annotations.Api;

@Api(value = "PersonController", description = "REST APIs related to Person Entity")
@RestController
public class PersonController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	public List<Person> listPersons = new ArrayList<Person>();

	@Autowired
	PersonService personneService;

	// Find
	@GetMapping("/persons")
	public	List<Person> findAllPersons() {
		try {
			LOGGER.info("begin findPersons");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end findPersons");
		}
		return listPersons;
	}

	@GetMapping("/person/{firstName}")
	public	Person findPersonneByFirstName(@PathVariable String firstName) {
		try {
			LOGGER.info("begin findPerson by first name");
			for (Person person : listPersons) {
				if (person.getFirstName().equals(firstName)) {
					return person;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end findPerson by first name");
		}
		return null;
	}

	@PostMapping("/person/add")
	public Person createPerson(@RequestBody PersonDetail personDetail) {
		try {
			LOGGER.info("begin createPerson");
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
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end createPerson");
		}
		return null;
	}

	@DeleteMapping("/person/delete/{firstName}/{lastName}")
	public Person deletePerson(@PathVariable String firstName, @PathVariable String lastName) {

		try {
			LOGGER.info("begin deletePerson");
			for (Person person : listPersons) {
				if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
					listPersons.remove(person);
					return person;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end deletePerson");
		}
		return null;
	}

	@PutMapping("/person/update/{firstName}/{lastName}/{address}/{city}/{zip}/{phone}/{email}")
	public Person updatePerson(@PathVariable String firstName, @PathVariable String lastName,
			@PathVariable String address, @PathVariable String city, @PathVariable String zip,
			@PathVariable String phone, @PathVariable String email) {

		try {
			LOGGER.info("begin updatePerson");
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
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end updatePerson");
		}
		return null;
	}

	@PostConstruct
	public void loadData() {

		try {
			LOGGER.info("begin PersonController.loadData");
			listPersons = personneService.loadPersons();

		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end PersonController.loadData");
		}

	}

}
