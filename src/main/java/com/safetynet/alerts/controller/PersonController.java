package com.safetynet.alerts.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
import com.safetynet.alerts.model.Root;

@RestController
public class PersonController {

	List<Person> listPersons = new ArrayList<Person>();

	// Find
	@GetMapping("/persons")
	List<Person> findAllPersonnes() {
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
	public Person createEmployee(@RequestBody PersonDescription personDescription) {
		Person newPerson = new Person(personDescription);
		listPersons.add(newPerson);
		return newPerson;
	}

	@DeleteMapping("/person/delete")
	public int deletePerson(@RequestBody PersonDescription personDescription) throws Exception{
		int index=0;
		int indexPerson=-1;
		for (Person person : listPersons) {			
			if (person.getFirstName().equals(personDescription.getFirstName())
					&& person.getLastName().equals(personDescription.getLastName())) {
				indexPerson = index;
			}
			index++;
		}
		listPersons.remove(indexPerson);
		return indexPerson;
	}

	@PutMapping("/person/update")
	public Person updatePerson(@RequestBody PersonDescription personDescription) throws Exception {
		for (Person person : listPersons) {
			if (person.getFirstName().equals(personDescription.getFirstName())
					&& person.getLastName().equals(personDescription.getLastName())) {
				person.setAddress(personDescription.getAddress());
				person.setCity(personDescription.getCity());
				person.setZip(personDescription.getZip());
				person.setPhone(personDescription.getPhone());
				person.setEmail(personDescription.getEmail());
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
