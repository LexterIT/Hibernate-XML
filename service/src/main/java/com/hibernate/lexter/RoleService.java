package com.hibernate.lexter;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class RoleService {

	private HibernateUtil hibernateUtil;
	private ScannerUtil scannerUtil;

	public RoleService(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
		this.scannerUtil = new ScannerUtil();
	}

	public RoleService(HibernateUtil hibernateUtil, ScannerUtil scannerUtil) {
		this.hibernateUtil = hibernateUtil;
		this.scannerUtil = scannerUtil;
	}

	public void setScannerUtil(ScannerUtil scannerUtil) {
		this.scannerUtil = scannerUtil;
	}

	public void roleMenu() {
		String action;
		while(true) {
			int id;
			showRoleActions();
			action = scannerUtil.getInputString("Choose an Action for Role:");
			switch(action.toUpperCase()) {
				case "ADD":
				String roleName = scannerUtil.getInputString("Title of new Role");
				Role role = createRole(roleName);
				addRole(role);
				break;
				case "REMOVE":
				id = scannerUtil.getInputInt("Enter the ID of Role you want to Delete");
				deleteRole(id);
				break;
				case "UPDATE":
				id = scannerUtil.getInputInt("Enter the ID of Role you want to Update");
				String rolereplacement = scannerUtil.getInputString("Enter the Role Replacement You want");
				updateRole(id, rolereplacement);
				break;
				case "VIEW":
				List<Role> roles = readRoles();
				for(Role rl : roles) 
					System.out.println(rl);
				break;
				case "EXIT":
				return;
			}
		}
	}
	
	public void showRoleActions() {
		System.out.println("Enter ADD\tto Add\ta Role");
    	System.out.println("Enter REMOVE\tto Remove\ta Role");
    	System.out.println("Enter UPDATE\tto Update\ta Role");
    	System.out.println("Enter VIEW\tto View\tall Role");
    	System.out.println("ENTER EXIT\tto Go back to Main Menu");
	}

	public Role createRole(String roleName) {
		Role role = new Role(roleName);
		return role;
	}

	public void addRole(Role role) {
		List<Role> curRoles = hibernateUtil.getObject(Role.class);
		boolean existing = false;
		for(Role tempRole : curRoles) {
			if(tempRole.toString().equalsIgnoreCase(role.toString())) {
				existing = true;
				System.out.println("The Role:" +role.toString() + " is already existing in the database");
				return;
			}
		}
		hibernateUtil.insertObject(role);
	}

	public List<Role> readRoles() {
		List<Role> roles = null;
		roles = hibernateUtil.getObject(Role.class);
		return roles;
	}

	public void updateRole(int id, String rolereplacement) {
		Role role =(Role) hibernateUtil.getSingleObject(Role.class, id);
		role.setRole(rolereplacement);
		hibernateUtil.updateObject(role);
	}

	public void deleteRole(int id) {	
		Role role =(Role) hibernateUtil.getSingleObject(Role.class, id);
		if(role != null) {
			deletePersonContainRole(role);
			hibernateUtil.deleteObject(role);
		} else {
			System.out.println("Role with this ID does not Exist!");
		}
	}

	public void deletePersonContainRole(Role role) {
		List<Person> peopleRole = (List<Person>) hibernateUtil.getObject(Person.class);
		String curRole = role.toString();
		for(Person person : peopleRole) {
			Set<Role> perRole = person.getRoles();
			List<Role> listRole = new ArrayList<Role>(person.getRoles());
			for(Role tempRole : listRole) {
				if(tempRole.toString().equals(role.toString())) {
					perRole.remove(tempRole);
				}
			}
			person.setRoles(perRole);
			hibernateUtil.updateObject(person);
		}
	}
}