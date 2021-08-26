package com.safetynet.alerts.model.output;

import java.util.ArrayList;

public class ResidentRepport {
	ArrayList<Resident> listResidents;
	public int adultsNb;
	public int childrenNb;
	public ArrayList<Resident> getListResidents() {
		return listResidents;
	}
	public void setListResidents(ArrayList<Resident> listResidents) {
		this.listResidents = listResidents;
	}
	public int getAdultsNb() {
		return adultsNb;
	}
	public void setAdultsNb(int adultsNb) {
		this.adultsNb = adultsNb;
	}
	public int getChildrenNb() {
		return childrenNb;
	}
	public void setChildrenNb(int childrenNb) {
		this.childrenNb = childrenNb;
	}

}
