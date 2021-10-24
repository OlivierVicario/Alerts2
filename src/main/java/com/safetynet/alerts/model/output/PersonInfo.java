package com.safetynet.alerts.model.output;

import java.util.Collections;
import java.util.List;



public class PersonInfo {
	String firstName;
	String lastName;
	int age;
	List<String> medications;
	List<String> allergies;
	
	public PersonInfo(Citizen citizen) {
		this.firstName = citizen.getFirstName();
		this.lastName = citizen.getLastName();
		this.age = citizen.getAge();
		this.medications = citizen.getMedications();
		this.allergies = citizen.getAllergies();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<String> getMedications() {
		return Collections.unmodifiableList(medications);
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<String> getAllergies() {
		return Collections.unmodifiableList(allergies);
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	

}
