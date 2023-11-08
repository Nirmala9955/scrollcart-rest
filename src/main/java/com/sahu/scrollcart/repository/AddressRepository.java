package com.sahu.scrollcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahu.scrollcart.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
