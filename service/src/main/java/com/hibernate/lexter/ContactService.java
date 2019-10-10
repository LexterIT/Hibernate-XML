package com.hibernate.lexter;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class ContactService {

	private HibernateUtil hibernateUtil;
	private ScannerUtil scannerUtil;

	public ContactService(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
		scannerUtil = new ScannerUtil();
	}

    public void setScannerUtil(ScannerUtil scannerUtil) {
        this.scannerUtil = scannerUtil;
    }

    public void updateContactMenu(Person person) {
		String action;
    	List<ContactType> contactTypes = hibernateUtil.getObject(ContactType.class);
    	List<ContactInfo> contactInfos = person.getContactInfo();
    	setContactTypes(contactTypes);

    	for(ContactInfo contacts : contactInfos) {
    		System.out.println(contacts.getIndex() + " " + contacts);
    	}
    	action = scannerUtil.getInputString("ADD? UPDATE? DELETE?");
    	switch(action.toUpperCase()) {
    		case "ADD":
    			ContactInfo newContactInfo = createContactInput();
    			contactInfos.add(newContactInfo);
    		break;
    		case "UPDATE":
    			editContactInput(contactInfos);
    		break;
    		case "DELETE":
	    	int index = scannerUtil.getInputInt("Enter Index of Contact to Delete");
    		contactInfos.remove(index);
    		break;
    	}

    	person.setContactInfo(contactInfos);
    }

    public ContactInfo createContactInput() {
    	String contactType = "", contactInfo;
    	List<ContactType> contactTypes = hibernateUtil.getObject(ContactType.class);
    	List<String> stringContactTypes = setContactTypes(contactTypes);	
        System.out.println(stringContactTypes);
        while(!stringContactTypes.contains(contactType)) {
	    	contactType = scannerUtil.getInputString("Please Choose a Contact Type");
    	}
	    contactInfo = scannerUtil.getInputString("Please Enter a Proper Contact Information");

	    ContactInfo contact = createContact(contactType, contactInfo);
	    return contact;
    }

    public ContactInfo createContact(String contactType, String contactInfo) {
    	ContactInfo contact = new ContactInfo(contactType, contactInfo);
	    return contact;
    }

    public void editContactInput(List<ContactInfo> contactInfos) {
    	String contactType = "", contactInfo = "";
        List<ContactType> contactTypes = hibernateUtil.getObject(ContactType.class);
        List<String> stringContactTypes = setContactTypes(contactTypes);
    	int index = scannerUtil.getInputInt("Enter Index of Contact to Update");
    	ContactInfo contact = contactInfos.get(index);
        while(!stringContactTypes.contains(contactType)) {
    		contactType = scannerUtil.getInputString("Enter new Contact Type");
		}		
    	contactInfo = scannerUtil.getInputString("Enter new Contact Information");
	    editContact(contact, contactType, contactInfo);
    	contactInfos.set(index, contact);
    }


    public void editContact(ContactInfo contact, String contactType, String contactInfo) {
		contact.setContactType(contactType);    
		if(!StringUtils.isEmpty(contactInfo))
	    	contact.setContactInfo(contactInfo);
    }

	public List<String> setContactTypes(List<ContactType> contactTypes) {
        List<String> stringContactTypes = new ArrayList<String>();
		for(ContactType contactType : contactTypes) {
			stringContactTypes.add(contactType.getContactType());
		}
        return stringContactTypes;
	}	


}