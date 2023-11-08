package com.sahu.scrollcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahu.scrollcart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
