package com.hibernate.lexter;

public class Name {

	private int id;
	private String lastName;
	private String firstName;
	private String midName = "";
	private String suffix = "";

	public Name() {
		
	}

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Name(String firstName, String lastName, String midName, String suffix) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.midName = midName;
		this.suffix = suffix;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}
	public String getMidName() {
		return midName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getSuffix() {
		return suffix;
	}

	public String toString() {
		return lastName+" "+firstName+" "+midName+" "+suffix;
	}

}