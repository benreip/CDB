package com.excilys.formation.cdb.dto;

public class UserDTO {
	private String id; 
	private String username;
	private String password;
	private RoleDTO role;


	public String getId() {
		return id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(final RoleDTO role) {
		this.role = role;
	}


}
