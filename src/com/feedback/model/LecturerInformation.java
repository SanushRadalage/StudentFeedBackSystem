package com.feedback.model;

import java.io.Serializable;

public class LecturerInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6970460026835978519L;
	
	int staffID;
	String name;
	
	public LecturerInformation(int staffID, String name) {
		super();
		this.staffID = staffID;
		this.name = name;
	}
	
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
