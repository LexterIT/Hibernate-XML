package com.hibernate.lexter;

/**
 * Hello world!
 *
 */

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class Person{
	private int id;
    private Name name;
    private Address address;
    private Date birthday;
    private Double gwa;
    private Date dateHired;
    private boolean isCurEmp;
    private List<ContactInfo> contactInfo;
    private Set<Role> roles;

    public Person() {
        contactInfo = new ArrayList<ContactInfo>();
        roles = new HashSet<Role>();
        name = new Name();
        address = new Address();
        gwa = 0.0;
        birthday = null;
        dateHired = null;
        isCurEmp = false;
    }

    public Person(Name name) {
        this();
    	this.name = name;
    }

    public void setId(int id) {
    	this.id = id;
    }
    public int getId() {
    	return id;
    }

    public void setName(Name name) {
    	this.name = name;
    }
    public Name getName() {
    	return name;
    }

    public void setAddress(Address address) {
    	this.address = address;
    }
    public Address getAddress() {
    	return address;
    }
    
    public void setBirthday(Date birthday) {
    	this.birthday = birthday;
    }
    public Date getBirthday() {
    	return birthday;
    }

    public void setGwa(Double gwa) {
    	this.gwa = gwa;
    }
    public Double getGwa() {
    	return gwa;
    }

    public void setDateHired(Date dateHired) {
    	this.dateHired = dateHired;
    }
    public Date getDateHired() {
    	return dateHired;
    }

    public void setIsCurEmp(boolean empStatus) {
    	isCurEmp = empStatus;
    }
    public boolean getIsCurEmp() {
    	return isCurEmp;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
    	this.contactInfo = contactInfo;
    }
    public List<ContactInfo> getContactInfo() {
    	return contactInfo;
    }

    public void setRoles(Set<Role> roles) {
    	this.roles = roles;
    }
    public Set<Role> getRoles() {
    	return roles;
    }

    public String toString() {
        String info = id + "\n" + "Name:" + name + "\tBirthday:" + birthday + "\tGWA:" + gwa +"\n";
        info += "Date Hired:" + dateHired + "\tCurrently Employed?" +isCurEmp +"\n";
        info += "Address:" + address + "\n";
        if(contactInfo.size() != 0) 
            info += "Contact Info:" + contactInfo + "\n";
        if(roles.size() != 0)
            info += "Roles:" + roles;
        return info;
    }

}
