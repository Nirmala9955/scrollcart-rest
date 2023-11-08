package com.sahu.scrollcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahu.scrollcart.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	public List<Permission> findByActive(Boolean active);

}
