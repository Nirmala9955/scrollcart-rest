package com.sahu.scrollcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahu.scrollcart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
