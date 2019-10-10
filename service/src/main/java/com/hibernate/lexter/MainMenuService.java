package com.hibernate.lexter;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class MainMenuService {
	
	private HibernateUtil hibernateUtil;
	private ScannerUtil scannerUtil;

	public MainMenuService(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
		scannerUtil = new ScannerUtil();
	}

    public void setScannerUtil(ScannerUtil scannerUtil) {
        this.scannerUtil = scannerUtil;
    }

	public List listPerson() {
		 List<Person> people = null;
        people = hibernateUtil.getSorted(Person.class, "id", "asc");
        for(Person person : people) {
            System.out.println(person);
        }
        return people;
	}

	public void PersonSortMenu() {
		String field = "", order = "";
		boolean valid = false;
		List<String> fields = new ArrayList<String>();
		List<Person> people;
		fields.add("lastname");
		fields.add("datehired");
		fields.add("gwa");
		while(!fields.contains(field.toLowerCase()))
			field = scannerUtil.getInputString("Enter which Field you want to Sort:(lastname, datehired, gwa)");
		while(valid == false) {
			order = scannerUtil.getInputString("Enter how do you want the order is:(ASC, DESC)");
			if(order.equalsIgnoreCase("ASC") || order.equalsIgnoreCase("DESC")) {
				valid = true;
			}
		}
		if(field.equalsIgnoreCase("gwa")){
			people = sortPersonGWA(order);
		} else {
			if(field.equalsIgnoreCase("lastname")) {
				field = "lastName";
			} else if(field.equalsIgnoreCase("datehired")) {
				field = "dateHired";
			}
			people = listPersonSort(field, order);
		}

		for(Person person : people) {
			System.out.println(person);
		}
	}

	public List<Person> listPersonSort(String field, String order) {
		List<Person> people = hibernateUtil.getSorted(Person.class, field, order);
		return people;
	}

	public List<Person> sortPersonGWA(String order) {
		Comparator<Person> comp;
		if(order.equalsIgnoreCase("asc"))
			comp = (Person p1, Person p2) -> p1.getGwa().compareTo(p2.getGwa());
		else 
			comp = (Person p1, Person p2) -> p2.getGwa().compareTo(p1.getGwa());
		List<Person> people = hibernateUtil.getObject(Person.class);
		Set<Person> treeSet = new TreeSet<Person>(comp);
		treeSet.addAll(people);
		people = new ArrayList<Person>(treeSet);
		return people;
	}

}