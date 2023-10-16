package com.pratibha.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pratibha.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("select p from Product p where p.category.categoryName=:category")
	List<Product> getProductsByCategory(String category);

	@Query("select p from Product p where p.productName like %:searchKey% or p.longDescription like %:searchKey% or p.shortDescription like %:searchKey% or p.category.categoryName like %:searchKey%")
	List<Product> getProductsBySearchKey(String searchKey);

	@Query("select p from Product p where p.category.categoryName=:category and ( p.productName like %:searchKey% or p.longDescription like %:searchKey% or p.shortDescription like %:searchKey%)")
	List<Product> getProductsUsingCategoryAndSearchKey(String category, String searchKey);
	
}
