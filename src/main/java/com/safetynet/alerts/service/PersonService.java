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
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonDetail;
import com.safetynet.alerts.model.Root;

/**
 * @author olvic
 *
 * create, read, update and delete persons
 */
@Service
public class PersonService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	public List<Person> listPersons = new ArrayList<Person>();
	
	/**
	 * @return List<Person>
	 */
	public	List<Person> findAllPersons() {
		try {
			LOGGER.info("begin findPersons");
			return loadPersons();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end findPersons");
		}
		return null;	
	}
	
	
	/**
	 * @param firstName
	 * @return Person
	 */
	public	Person findPersonneByFirstName(String firstName) {
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
	

	/**
	 * @param personDetail
	 * @return Person
	 */
	public Person createPerson(PersonDetail personDetail) {
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

	/**
	 * @param firstName
	 * @param lastName
	 * @return Person
	 */
	public Person deletePerson(String firstName,  String lastName) {

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

	/**
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param zip
	 * @param phone
	 * @param email
	 * @return Person
	 */
	public Person updatePerson(String firstName, String lastName,
			String address, String city, String zip,
			 String phone, String email) {

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
	
	
	/**
	 * @return List<Person>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@PostConstruct
	public List<Person> loadPersons() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		// lecture fichier local
		Root root = mapper.readValue(new File("data.json"), Root.class);
		
		 //lecture fichier distant

		/*URL distantInputDataURL = new URL(
				"https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
		root = mapper.readValue(distantInputDataURL.openStream(), Root.class);*/
		
		listPersons =root.getPersons();
		return listPersons;
	}
}
