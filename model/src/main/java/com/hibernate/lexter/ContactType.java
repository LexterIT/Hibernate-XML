package com.hibernate.lexter;


public class ContactType {

	private int id;
	private String contactType;

	public ContactType() {
		contactType = "";
	}

	public ContactType(String contactType) {
		this.contactType = contactType;
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

	public String toString() {
		return this.id+".\t"+this.contactType;
	}

}