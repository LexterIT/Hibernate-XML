package com.hibernate.lexter;

public class Role {

	private int id;
	private String role;

	public Role() {
		role = "";
	}

	public Role(String role) {
		this.role = role;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}

	public String toString() {
		return this.role;
	}

	// @Override
	// public boolean equals(Object obj) {
	// 	System.out.println("Comparing");
	// 	Role rl = (Role) obj;
	// 	return rl.role.equals(this.role);
	// }

	@Override
	public int hashCode() {
		return (int) role.hashCode();
	}

}