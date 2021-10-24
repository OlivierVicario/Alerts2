package com.safetynet.alerts.model.output;

import java.util.List;

public class Child {
	String firstName;
	String lastName;
	int age;
	List<String> foyerMembers;

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

	public List<String> getFoyerMembers() {
		return foyerMembers;
	}

	public void setFoyerMembers(List<String> foyerMembers) {
		this.foyerMembers = foyerMembers;
	}

}
