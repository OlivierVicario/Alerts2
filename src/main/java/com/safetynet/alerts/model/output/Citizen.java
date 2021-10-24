package com.safetynet.alerts.model.output;

import java.time.LocalDate;
import java.util.List;

public class Citizen {
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String zip;
	public String phone;
	public String email;
	public String sBirthdate;
	public LocalDate birthdate;
	public int age;
	public Boolean isAdult;
	public List<String> medications;
	public List<String> allergies;
	public String station;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getsBirthdate() {
		return sBirthdate;
	}
	public void setsBirthdate(String sBirthdate) {
		this.sBirthdate = sBirthdate;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public Boolean getIsAdult() {
		return isAdult;
	}
	public void setIsAdult(Boolean isAdult) {
		this.isAdult = isAdult;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	@Override
	public String toString() {
		return "Citizen [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", email=" + email + ", sBirthdate=" + sBirthdate
				+ ", birthdate=" + birthdate + ", age=" + age + ", isAdult=" + isAdult + ", medications=" + medications
				+ ", allergies=" + allergies + ", station=" + station + "]";
	}


}
