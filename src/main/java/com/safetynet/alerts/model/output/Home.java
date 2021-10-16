package com.safetynet.alerts.model.output;

import java.util.Collections;
import java.util.List;

public class Home {
	String address;
	List<Patient> members;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Patient> getMembers() {
		//return Collections.unmodifiableList(members);
		return members;
	}
	public void setMembers(List<Patient> members) {
		this.members = members;
	}
}
