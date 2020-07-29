package com.excilys.formation.cdb.mapper;


import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.UserDTO;
import com.excilys.formation.cdb.modele.User;
@Component
public class MapperUser {

	public  UserDTO toDto (final User d ) {
		final UserDTO u = new UserDTO();
		u.setUsername(d.getUsername());
		u.setId(Integer.toString(d.getId()));
		u.setPassword(d.getPassword());
		u.setRole(MapperRole.toDto(d.getRole()));
		return u;
	}


	public static User toEntity (final UserDTO u ) {
		final User d = new User();
		d.setId(Integer.parseInt(u.getId()));
		d.setUsername(u.getUsername());
		d.setPassword(u.getPassword());
		d.setRole(MapperRole.toEntity(u.getRole()));
		return d;
	}
}
