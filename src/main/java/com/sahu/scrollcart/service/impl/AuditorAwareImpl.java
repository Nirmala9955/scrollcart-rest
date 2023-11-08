package com.sahu.scrollcart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import com.sahu.scrollcart.constants.GMartConstants;
import com.sahu.scrollcart.model.User;
import com.sahu.scrollcart.security.SecurityUtil;
import com.sahu.scrollcart.service.UserService;
import com.sahu.scrollcart.service.dto.CustomUserDetailsDTO;

@Service
public class AuditorAwareImpl implements AuditorAware<Long> {
	
	@Autowired
	private UserService userService;
	
	@Override
	public Optional<Long> getCurrentAuditor() {
		CustomUserDetailsDTO userDetailsDTO = SecurityUtil.getCurrentUser();
		if (userDetailsDTO != null) {
			return Optional.of(userDetailsDTO.getUserId());
		} else {
			User user = userService.findByEmail(GMartConstants.SUPPORT_MAIL);
			return Optional.of(user.getId());
		}
	}

}
