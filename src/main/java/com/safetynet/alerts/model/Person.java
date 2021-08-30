package com.safetynet.alerts.model;

public class Person{
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String zip;
    public String phone;
    public String email;
    public Person() {}
	/*public Person(PersonDescription personDescription) {
		this.setFirstName(personDescription.getFirstName());
		this.setLastName(personDescription.getLastName());
		this.setAddress(personDescription.getAddress());
		this.setCity(personDescription.getCity());
		this.setZip(personDescription.getZip());
		this.setPhone(personDescription.getPhone());
		this.setEmail(personDescription.getEmail());
	}*/
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
    
    
    
}