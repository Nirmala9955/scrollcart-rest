package com.sahu.scrollcart.service;

import java.util.List;

import com.sahu.scrollcart.model.AppParamValue;

public interface AppParamValueService {

	public List<AppParamValue> findByAppParamGroupName(String appParamGroupName);

}
