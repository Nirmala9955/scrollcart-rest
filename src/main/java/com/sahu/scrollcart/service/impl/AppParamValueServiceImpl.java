package com.sahu.scrollcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahu.scrollcart.model.AppParamValue;
import com.sahu.scrollcart.repository.AppParamValueRepository;
import com.sahu.scrollcart.service.AppParamValueService;

@Service
public class AppParamValueServiceImpl implements AppParamValueService {

	@Autowired
	private AppParamValueRepository appParamValueRepository;

	@Override
	public List<AppParamValue> findByAppParamGroupName(String appParamGroupName) {
		return appParamValueRepository.findByAppParamGroupName(appParamGroupName);
	}

}
