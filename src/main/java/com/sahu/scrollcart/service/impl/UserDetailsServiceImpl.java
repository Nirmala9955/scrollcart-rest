package com.sahu.scrollcart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sahu.scrollcart.model.Permission;
import com.sahu.scrollcart.model.Role;
import com.sahu.scrollcart.model.User;
import com.sahu.scrollcart.service.UserService;
import com.sahu.scrollcart.service.dto.CustomUserDetailsDTO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public CustomUserDetailsDTO loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("Inside loadUserByUsername() method");
		LOGGER.info("username - " + username);
		User user = userService.findByEmail(username);
		LOGGER.info("User - " + user);

		if (user == null) {
			throw new UsernameNotFoundException("User is not exist");
		} else if (!user.getActive()) {
			throw new UsernameNotFoundException("User is not active, please contact admin!");
		} else {
			Long loggedInUserId = user.getId();

			List<GrantedAuthority> authorities = user.getRoles().stream()
					.flatMap(role -> role.getPermissions().stream()
							.map(permission -> new SimpleGrantedAuthority(permission.getName())))
					.collect(Collectors.toList());

			List<String> userRoles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
			List<String> userPermissions = user.getRoles().stream()
					.flatMap(role -> role.getPermissions().stream().map(Permission::getName))
					.collect(Collectors.toList());

			String fullName = user.getFirstName() + " " + user.getLastName();
			LOGGER.info("User object created");
			return new CustomUserDetailsDTO(username, user.getPassword(), authorities, loggedInUserId, user.getUuid(),
					fullName, user.getEmail(), user.getPhoneNo(), userRoles, userPermissions);
		}
	}

}
