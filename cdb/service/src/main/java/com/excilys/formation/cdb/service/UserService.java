package com.excilys.formation.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.dto.UserDTO;
import com.excilys.formation.cdb.mapper.MapperUser;
import com.excilys.formation.cdb.modele.CustomUserDetails;
import com.excilys.formation.cdb.modele.Role;
import com.excilys.formation.cdb.modele.User;
import com.excilys.formation.cdb.persistence.UserDAO;

@Service
public class UserService implements UserDetailsService{
	private final UserDAO userDao;
	private final MapperUser mapUser;

	@Autowired
	public UserService(final UserDAO userDao, final MapperUser mapUser) {
		this.userDao = userDao;
		this.mapUser = mapUser;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userDao.findUserByName(username);
		final CustomUserDetails userDetails = new CustomUserDetails(user);
		return  userDetails;
	}


	public User addUser(final UserDTO userDto) {
		final User user = mapUser.toEntity(userDto);
		return userDao.create(user);
	}

	public List<Role> getRoles() {
		return userDao.getRoles();
	}
}
