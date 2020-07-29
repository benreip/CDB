package com.excilys.formation.cdb.mapper;

import com.excilys.formation.cdb.dto.RoleDTO;
import com.excilys.formation.cdb.modele.Role;

public class MapperRole {

	public static RoleDTO toDto(final Role r) {
		final RoleDTO u = new RoleDTO();
		u.setRoleId(Integer.toString(r.getRoleId()));
		u.setRoleName(r.getRoleName());
		return u;
	}

	public static Role toEntity(final RoleDTO r) {
		final Role u = new Role();
		u.setRoleId(Integer.parseInt(r.getRoleId()));
		u.setRoleName(r.getRoleName());
		return u;
	}
}
