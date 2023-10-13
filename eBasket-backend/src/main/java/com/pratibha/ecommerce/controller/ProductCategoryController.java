package com.pratibha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.ProductCategory;
import com.pratibha.ecommerce.service.ProductCategoryService;

@RestController
@RequestMapping("/ecommerce")
public class ProductCategoryController {
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping("/productCategory/all")
	public ResponseEntity<List<ProductCategory>> getAllProductCategory(){
		List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
		return ResponseEntity.ok(productCategories);
	}
 
}
