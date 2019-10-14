package com.hibernate.lexter;


import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;

public class PersonService {
	
	private HibernateUtil hibernateUtil;
	private NameService nameService;
	private AddressService addressService;
    private ContactService contactService;
    private PersonRoleService personRoleService;
    private ScannerUtil scannerUtil;

	public PersonService(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
        nameService = new NameService();
        addressService = new AddressService();
        contactService = new ContactService(hibernateUtil);
        personRoleService = new PersonRoleService(hibernateUtil);
        scannerUtil = new ScannerUtil();
	}


    public void setScannerUtil(ScannerUtil scannerUtil) {
        this.scannerUtil = scannerUtil;
    }


	public void personMenu() {
		String action;
		while(true) {
            int id;
			showPersonActions();
			action = scannerUtil.getInputString("Choose an Action for Person");
			switch(action.toUpperCase()) {
				case "ADD":
					addPerson();
				break;
				case "REMOVE":
                    id = scannerUtil.getInputInt("Enter the ID of Person you want to Delete");
                    deletePerson(id);
				break;
				case "UPDATE":
                    updatePersonInput();
				break;
				case "VIEW":
                    id = scannerUtil.getInputInt("Enter the ID of Person you want to View");
					Person person = readPerson(id);
                    if(person != null)
                        System.out.println(person);
                    else 
                        System.out.println("Person with this ID does not exist!");
				break;
				case "EXIT":
					return;
			}
		}
	}

	public void showPersonActions() {    	
    	System.out.println("Enter ADD\tto Add\ta Person");
    	System.out.println("Enter REMOVE\tto Remove\ta Person");
    	System.out.println("Enter UPDATE\tto Update\ta Person");
    	System.out.println("Enter VIEW\tto View\ta Person");
    	System.out.println("ENTER EXIT\tto Go back to Main Menu");
    }

    public void addPerson() {
        String choice;
    	List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
    	Set<Role> personRoles = new HashSet<Role>();
        Date birthday = null, dateHired = null; 
        Person person;
    	Name name = nameService.createNameInput();
    	Address address = addressService.createAddressInput();
    	Double gwa = scannerUtil.getInputDouble("PLease Enter GWA");

        while(birthday == null)
            birthday = scannerUtil.parseDate("Please Enter Birthday");

        while(dateHired == null) 
        	dateHired = scannerUtil.parseDate("Please Enter dateHired");

    	boolean isCurEmp = scannerUtil.getInputBoolean("Is Currently Employed? ('True' or else 'False')");

        choice = scannerUtil.getInputString("Do you want to add Contact? ('Yes' or 'No')");
    	if(choice.equalsIgnoreCase("yes")) {
            while(!choice.equalsIgnoreCase("NO")) {
        		contactInfos.add(contactService.createContactInput());
    	    	choice = scannerUtil.getInputString("Do you want to add more? ('Yes' or 'No')");
        	}
        }

        choice = scannerUtil.getInputString("Do you want to add a Role? ('Yes' or 'No')");
        if(choice.equalsIgnoreCase("Yes")){
            personRoles = personRoleService.createPersonRoleInput(personRoles);
        }

        person = createPerson(name, address, gwa, birthday, dateHired, isCurEmp, contactInfos, personRoles);
    	hibernateUtil.insertObject(person);
    }

    public Person createPerson(Name name, Address address, Double gwa, Date birthday, Date dateHired, boolean status, List<ContactInfo> contactInfos, Set<Role> roles) {
        Person person = new Person(name);
        person.setAddress(address);
        person.setBirthday(birthday);
        person.setDateHired(dateHired);
        person.setGwa(gwa);
        person.setIsCurEmp(status);
        person.setContactInfo(contactInfos);
        person.setRoles(roles);
        return person;
    }

    public Person readPerson(int id) {
        Person person =(Person) hibernateUtil.getSingleObject(Person.class, id);
		return person;
    }

    public void updatePersonInput() {
        int id = 0;
        boolean valid = false;
        List<Person> people = hibernateUtil.getObject(Person.class);
        Person person = null;
        while(valid != true) {
            id = scannerUtil.getInputInt("Enter the ID of Person you want to Update:");
            person =(Person) hibernateUtil.getSingleObject(Person.class, id);
            if(person != null) 
                valid = true;
            else {
                System.out.println("Person ID does not exist!");
                valid = false;
            }
        }
        System.out.println("NAME? ADDRESS? GWA? BIRTHDAY? DATEHIRED? STATUS? CONTACT? ROLE?"); 
        String colChoice = scannerUtil.getInputString("Enter which Column you want to Update"); 
        updatePerson(person, colChoice);
    }

    public void updatePerson(Person person, String colChoice) {
    	System.out.println("If you want a blank input leave a whitespace \' \'");
    	switch(colChoice.toUpperCase()) {
    		case "NAME":
	    		nameService.updateNameInput(person.getName());
    		break;
    		case "ADDRESS":
    			addressService.updateAddressInput(person.getAddress());
    		break;
    		case "BIRTHDAY":
    			Date birthday = scannerUtil.parseDate("Enter Updated Birthday:");
    			person.setBirthday(birthday);
    		break;
    		case "GWA":
    			double gwa = scannerUtil.getInputDouble("Enter Updated GWA:");
    			person.setGwa(gwa);
    		break;
    		case "DATEHIRED":
    			Date dateHired = scannerUtil.parseDate("Enter Updated Date Hire:");
    			person.setDateHired(dateHired);
    		break;
    		case "STATUS":
    			boolean status = scannerUtil.getInputBoolean("Enter Updated Status:");
    			person.setIsCurEmp(status);
    		break;
    		case "CONTACT":
                contactService.updateContactMenu(person);
    		break;
    		case "ROLE":
                personRoleService.updateRoleMenu(person);
    		break;
    	}
    	hibernateUtil.updateObject(person);
    }

    public void deletePerson(int id) {
        Person person =(Person) hibernateUtil.getSingleObject(Person.class, id);
        try {
            hibernateUtil.deleteObject(person);
        } catch(IllegalArgumentException e) {
            System.out.println("Person with this ID does not exist!");
            return;
        }
    }

}