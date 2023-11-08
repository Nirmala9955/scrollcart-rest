package com.sahu.scrollcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sahu.scrollcart.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByUserName(String userName);

	public Optional<User> findByOtp(String otp);
	
}
