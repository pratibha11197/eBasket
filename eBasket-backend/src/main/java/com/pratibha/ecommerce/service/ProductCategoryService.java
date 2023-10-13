package com.pratibha.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratibha.ecommerce.entity.ProductCategory;
import com.pratibha.ecommerce.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	public List<ProductCategory> getAllProductCategory(){
		return productCategoryRepository.findAll();
	}
}
