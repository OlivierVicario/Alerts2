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

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonDetail;
import com.safetynet.alerts.service.PersonService;

import io.swagger.annotations.Api;

@Api(value = "PersonController", description = "REST APIs related to Person Entity")
@RestController
public class PersonController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	public PersonService personService;


	@GetMapping("/persons")
	public List<Person> findAllPersons() {
		try {
			LOGGER.info("begin findPersons");
			return personService.loadPersons();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end findPersons");
		}
		return null;
	}

	@GetMapping("/person/{firstName}")
	public Person findPersonneByFirstName(@PathVariable String firstName) {
		try {
			LOGGER.info("begin findPerson by first name");
			return personService.findPersonneByFirstName(firstName);
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
			return personService.createPerson(personDetail);
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
			return personService.deletePerson(firstName, lastName);
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
			return personService.updatePerson(firstName, lastName, address, city, zip, phone, email);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end updatePerson");
		}
		return null;
	}

}
