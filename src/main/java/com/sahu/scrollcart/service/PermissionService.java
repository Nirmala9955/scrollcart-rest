package com.sahu.scrollcart.service;

import java.util.List;

import com.sahu.scrollcart.model.Permission;

public interface PermissionService {

	public List<Permission> findAll();

	public List<Permission> findByIdIn(List<Long> ids);

}
