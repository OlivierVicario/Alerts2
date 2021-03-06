package com.safetynet.alerts.model.output;

import java.util.Collections;
import java.util.List;



public class Patient {
	String firstName;
	String lastName;
	String phone;
	int age;
	List<String> medications;
	List<String> allergies;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public Patient(Citizen citizen) {
		this.firstName = citizen.getFirstName();
		this.lastName = citizen.getLastName();
		this.phone = citizen.getPhone();
		this.age = citizen.getAge();
		this.medications = citizen.getMedications();
		this.allergies = citizen.getAllergies();
	}
}
