package com.sahu.scrollcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahu.scrollcart.model.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

}
