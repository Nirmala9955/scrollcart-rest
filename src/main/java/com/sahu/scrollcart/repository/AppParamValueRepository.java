package com.sahu.scrollcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahu.scrollcart.model.AppParamValue;

public interface AppParamValueRepository extends JpaRepository<AppParamValue, Long> {

	public List<AppParamValue> findByAppParamGroupName(String appParamGroupName);
	
}
