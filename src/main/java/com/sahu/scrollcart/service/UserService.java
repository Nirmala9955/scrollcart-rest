package com.sahu.scrollcart.service;

import com.sahu.scrollcart.dto.request.SignUpRequestDTO;
import com.sahu.scrollcart.model.User;

public interface UserService {

	public User findByEmail(String email);
	
	public User registerUser(SignUpRequestDTO user);
	
	public User findByOtp(String otp);
	
	public Boolean activateUser(User user);
	
	public String getResetPasswordCode(User user);

	public Boolean updatePassword(User user, String newPassword);
	
}
