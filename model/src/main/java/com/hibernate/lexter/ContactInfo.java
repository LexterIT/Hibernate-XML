package com.hibernate.lexter;

import java.util.List;
import java.util.ArrayList;

public class ContactInfo {

	private int id;
	private String contactType;
	private String contactInfo;
	private int index;

	public ContactInfo() {
		contactType = "";
		contactInfo = "";	
	}

	public ContactInfo(String contactType, String contactInfo) {
		this.contactType = contactType;
		this.contactInfo = contactInfo;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContactType() {
		return contactType;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getContactInfo() {
		return contactInfo;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String toString() {
		return contactType + ":" + contactInfo;
	}

}