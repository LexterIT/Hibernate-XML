package com.hibernate.lexter;

/**
 * Hello world!
 *
 */

import java.util.List;

public class App 
{

    private static HibernateUtil hibernateUtil;

    public static void main( String[] args )
    {
        hibernateUtil = new HibernateUtil();
        ScannerUtil scannerUtil = new ScannerUtil();
    	App app = new App(); 
        PersonService personService = new PersonService(hibernateUtil);
        RoleService roleService = new RoleService(hibernateUtil);
        MainMenuService mainMenuService = new MainMenuService(hibernateUtil);
    	String action;

    	while(true) {
            app.showListActions();
    		action = scannerUtil.getInputString("Choose an Action");
    		switch(action.toUpperCase()) {
    			case "PRINT":
                    mainMenuService.listPerson();
    			break;
    			case "SORT":
                    mainMenuService.PersonSortMenu();
    			break;
    			case "PERSON":
                    personService.personMenu();
    			break;
    			case "ROLE":
                    roleService.roleMenu();
    			break;
    		}
    	}
    }

    public void showListActions() {
    	System.out.println("Enter PRINT\tto Print List of Person");
    	System.out.println("Enter SORT\tto Sort\tList of Person");
    	System.out.println("Enter PERSON\tto Perform on Person");
    	System.out.println("Enter ROLE\tto Perform on Roles");
    }

   
}
