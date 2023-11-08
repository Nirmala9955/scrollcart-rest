package com.sahu.scrollcart.service;

import com.sahu.scrollcart.model.Role;

public interface RoleService {

	public Role findByName(String name);

	public Role findById(Long id);

}
