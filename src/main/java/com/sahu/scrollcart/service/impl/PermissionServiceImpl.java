package com.sahu.scrollcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahu.scrollcart.model.Permission;
import com.sahu.scrollcart.repository.PermissionRepository;
import com.sahu.scrollcart.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findByActive(true);
	}

	@Override
	public List<Permission> findByIdIn(List<Long> ids) {
		return permissionRepository.findAllById(ids);
	}

}
