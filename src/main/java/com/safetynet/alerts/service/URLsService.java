package com.safetynet.alerts.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.Root;
import com.safetynet.alerts.model.output.Child;
import com.safetynet.alerts.model.output.Citizen;
import com.safetynet.alerts.model.output.Home;
import com.safetynet.alerts.model.output.Patient;
import com.safetynet.alerts.model.output.PersonInfo;
import com.safetynet.alerts.model.output.Resident;
import com.safetynet.alerts.model.output.ResidentRepport;

/**
 * @author olvic
 *
 *charge les donnees et construit une liste de synthese à l'aide de la classe resident.
 *ensuite à chaque appel de requete la reponse est construite à l'aide des classes intermediaires
 *et de classe de rapport si necessaire
 */


@Service
public class URLsService {
	List<Person> listPersons = new ArrayList<Person>();
	List<Firestation> listFirestations = new ArrayList<Firestation>();
	List<Medicalrecord> listMedicalrecords = new ArrayList<Medicalrecord>();

	List<Citizen> listCitizens = new ArrayList<Citizen>();


	/**
	 * @param station
	 * @return ResidentRepport
	 * @throws ParseException
	 */
	public	ResidentRepport findResidentsByFirestation(String station)
			throws ParseException {
		ResidentRepport residentRepport = new ResidentRepport();
		ArrayList<Resident> residents = new ArrayList<Resident>();

		for (Citizen citizen : listCitizens) {
			if (citizen.getStation().equals(station)) {
				Resident resident = new Resident(citizen);
				residents.add(resident);
				if (citizen.isAdult)
					residentRepport.adultsNb++;
				else
					residentRepport.childrenNb++;
			}

		}
		residentRepport.setListResidents(residents);
		return residentRepport;
	}


	/**
	 * @param address
	 * @return ArrayList<Child>
	 */
	public	ArrayList<Child> findChildrenByAddress(String address) {
		ArrayList<Child> children = new ArrayList<Child>();
		List<Citizen> citizens_1 = listCitizens.subList(0, listCitizens.size());
		for (Citizen citizen : listCitizens) {
			if (citizen.getAddress().equals(address) && citizen.getAge() < 19) {
				Child child = new Child();
				child.setFirstName(citizen.getFirstName());
				child.setLastName(citizen.getLastName());
				child.setAge(citizen.getAge());
				child.setFoyerMembers(new ArrayList<String>());

				for (Citizen foyerMember : citizens_1) {
					if (foyerMember.getAddress().equals(address)) {
						if (!foyerMember.getFirstName().equals(child.getFirstName())) {
							child.getFoyerMembers().add(foyerMember.getFirstName() + " " + foyerMember.getLastName());
						}
					}
				}
				children.add(child);
			}
		}
		return children;
	}

	/**
	 * @param station
	 * @return ArrayList<String>
	 */
	public	ArrayList<String> findPhonesByFireStation( String station) {
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		for (Citizen citizen : listCitizens) {
			if (citizen.getStation().equals(station))
				phoneNumbers.add(citizen.phone);
		}
		return phoneNumbers;
	}

	/**
	 * @param station
	 * @return List<Patient>
	 * @throws ParseException
	 */
	public List<Patient> findPatientsByFirestation(String station)
			throws ParseException {
		ArrayList<Patient> patients = new ArrayList<Patient>();

		for (Citizen citizen : listCitizens) {
			if (citizen.getStation().equals(station)) {
				Patient patient = new Patient(citizen);
				patients.add(patient);
			}
		}
		return patients;
	}

	
	/**
	 * @param stations
	 * @return List<Home>
	 * @throws ParseException
	 */
	public	List<Home> findHomesByFirestation(String[] stations)
			throws ParseException {
		
		ArrayList<Home> homes = new ArrayList<Home>();

		for (Firestation firestation : listFirestations) {
			for (String station : stations) {
				if (firestation.getStation().equals(station)) {
					Home home = new Home();
					home.setAddress(firestation.getAddress());
					home.setMembers(new ArrayList<Patient>());
					for (Citizen citizen : listCitizens) {
						if (citizen.getAddress().equals(home.getAddress())) {
							Patient patient = new Patient(citizen);
							home.getMembers().add(patient);
						}
					}
					homes.add(home);
				}
			}
		}
		return homes;
	}

	
	/**
	 * @param firstName
	 * @param lastName
	 * @return List<PersonInfo>
	 * @throws ParseException
	 */
	public	List<PersonInfo> findPersonInfosByNames(String firstName,String lastName)
			throws ParseException {

		ArrayList<PersonInfo> personInfos = new ArrayList<PersonInfo>();

		for (Citizen citizen : listCitizens) {
			if (citizen.getFirstName().equals(firstName)&&citizen.getLastName().equals(lastName)) {
				PersonInfo personInfo = new PersonInfo(citizen);
				personInfos.add(personInfo);
			}
		}
		return personInfos;
	}
	
	
	/**
	 * @param city
	 * @return List<String> 
	 */
	public	List<String> findEmailsByCity(@RequestParam(value="city") String city){
		List<String> emails = new ArrayList<String>();
		for (Citizen citizen : listCitizens) {
			if (citizen.getCity().equals(city)) {
				emails.add(citizen.getEmail());
			}
		}
		return emails;		
	}
	
	
	/**
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ParseException
	 */
	@PostConstruct
	public void loadData() throws JsonParseException, JsonMappingException, IOException, ParseException {
		ObjectMapper mapper = new ObjectMapper();

		Root root = mapper.readValue(new File("data.json"), Root.class);

		listFirestations = root.getFirestations();
		listPersons = root.getPersons();
		listMedicalrecords = root.getMedicalrecords();
		
		for (Person person : listPersons) {
			Citizen citizen = new Citizen();
			citizen.setFirstName(person.getFirstName());
			citizen.setLastName(person.getLastName());
			citizen.setAddress(person.getAddress());
			citizen.setCity(person.getCity());
			citizen.setZip(person.getZip());
			citizen.setPhone(person.getPhone());
			citizen.setEmail(person.getEmail());
			for (Firestation firestation : listFirestations) {
				if (firestation.getAddress().equals(person.getAddress())) {
					citizen.setStation(firestation.getStation());
				}
			}
			for (Medicalrecord record : listMedicalrecords) {
				if (record.getFirstName().equals(citizen.getFirstName())
						&& record.getLastName().equals(citizen.getLastName())) {
					Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(record.getBirthdate());
					int age = new Date().getYear() - birthDate.getYear();
					citizen.setAge(age);
					Date birthday_19 = new Date(birthDate.getYear() + 19, birthDate.getMonth(), birthDate.getDay());
					if (birthday_19.after(new Date()))
						citizen.setIsAdult(false);
					else
						citizen.setIsAdult(true);
					citizen.setMedications(record.getMedications());
					citizen.setAllergies(record.getAllergies());

				}
			}
			listCitizens.add(citizen);
		}
	}
}
