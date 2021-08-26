package com.safetynet.alerts.model;

import java.util.List;

public class Root {
    public List<Person> persons;
    public List<Firestation> firestations;
    public List<Medicalrecord> medicalrecords;
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	public List<Firestation> getFirestations() {
		return firestations;
	}
	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}
	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}
	public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
    
    
}
