package com.pratibha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratibha.ecommerce.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{
	

}
