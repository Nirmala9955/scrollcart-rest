package com.sahu.scrollcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahu.scrollcart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
