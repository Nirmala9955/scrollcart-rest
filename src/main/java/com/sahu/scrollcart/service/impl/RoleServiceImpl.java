package com.sahu.scrollcart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahu.scrollcart.model.Role;
import com.sahu.scrollcart.repository.RoleRepository;
import com.sahu.scrollcart.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		Optional<Role> optionalRole = roleRepository.findByName(name);
		return optionalRole.isPresent() ? optionalRole.get() : null;
	}

	@Override
	public Role findById(Long id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
		return optionalRole.isPresent() ? optionalRole.get() : null;
	}

}
