package com.hibernate.lexter;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class PersonRoleService {
	
	private HibernateUtil hibernateUtil;
	private ScannerUtil scannerUtil;

	public PersonRoleService(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
		scannerUtil = new ScannerUtil();
	}	

    public void setScannerUtil(ScannerUtil scannerUtil) {
        this.scannerUtil = scannerUtil;
    }


	public void updateRoleMenu(Person person) {
		String action;
		int index;
		Set<Role> roles = person.getRoles();
		List<Role> rolesTemp = new ArrayList<Role>(roles);
		System.out.println(rolesTemp);
		action = scannerUtil.getInputString("ADD? DELETE?");
		switch(action.toUpperCase()) {
			case "ADD":
				roles = createPersonRoleInput(roles);
			break;
			case "DELETE":
				index = scannerUtil.getInputInt("Enter Index of Role to Delete");
				rolesTemp.remove((index-1));
				roles.retainAll(rolesTemp);
			break;
			default:
				System.out.println("Invalid action");
				return;
		}
		person.setRoles(roles);
	}

    public Set<Role> createPersonRoleInput(Set<Role> curRoles) {
		String roleChoice = "";	
    	String choice = "yes";
    	List<Role> roles = hibernateUtil.getObject(Role.class);
    	List<Role> tempList = new ArrayList<Role>(curRoles);
    	List<String> tempString = toRoleString(tempList);
    	Role tempRole;
    	int roleId;
    	List<String> rolesString = toRoleString(roles);
    	System.out.println(roles);
    	while(!choice.equalsIgnoreCase("no")) {
    		if(choice.equalsIgnoreCase("yes")) {
    			// while(!checkContains(rolesString, roleChoice))
    			while(!rolesString.contains(roleChoice)) {
					roleChoice = scannerUtil.getInputString("Please Choose a Role");
				}
		    	roleId = rolesString.indexOf(roleChoice);
				tempRole = createPersonRole(roles, roleId);
				// if(!curRoles.add(tempRole)) 
				if(checkContains(tempString,roleChoice)){
					System.out.println("This Person already have the Role:"+roleChoice);
				} else {
					curRoles.add(tempRole);
					System.out.println(curRoles);
				}
				roleChoice = "";
				choice = scannerUtil.getInputString("Do you want to add another? ('Yes' or 'No')");
    		} else {
    			System.out.println("Invalid");
				choice = scannerUtil.getInputString("Do you want to try again? ('Yes' or 'No')");
    		}
    	}
    	return curRoles;
    }

    public Role createPersonRole(List<Role> roles, int roleId) {
    	Role role = roles.get(roleId);
    	return role;
    }

	private List<String> toRoleString(List<Role> roles) {
		List<String> rolesString = new ArrayList<String>();
		for(Role role : roles) {
			rolesString.add(role.getRole());
		}
		return rolesString;
	}

	private boolean checkContains(List<String> rolesString, String role) {
		for(String str : rolesString) {
			if(str.equalsIgnoreCase(role)) 
				return true;
		}
		return false;
	}



}