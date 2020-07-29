package com.excilys.formation.cdb.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer roleId;

	@Column(name="name")
	private String roleName;

	public Role() {

	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(final int id) {
		this.roleId = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(final String name) {
		this.roleName = name;
	}


}
