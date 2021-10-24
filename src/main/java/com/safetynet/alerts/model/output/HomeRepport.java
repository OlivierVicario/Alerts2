package com.safetynet.alerts.model.output;

import java.util.Collections;
import java.util.List;

public class HomeRepport {
	String firestation;
	List<Home> listHomes;
	
	public String getFirestation() {
		return firestation;
	}
	public void setFirestation(String firestation) {
		this.firestation = firestation;
	}
	public List<Home> getListHomes() {
		return Collections.unmodifiableList(listHomes);
	}
	public void setListHomes(List<Home> listHomes) {
		this.listHomes = listHomes;
	}

}
