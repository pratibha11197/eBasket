package com.pratibha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.ProductCategory;
import com.pratibha.ecommerce.requestresponse.ResponseHandler;
import com.pratibha.ecommerce.service.ProductCategoryService;

@RestController
@RequestMapping("/ecommerce")
public class ProductCategoryController {
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping("/productCategory/all")
	public ResponseEntity<ResponseHandler> getAllProductCategory(){
		List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
		
		if(productCategories != null)
			return new ResponseEntity<>(new ResponseHandler(true, "OK", productCategories), HttpStatus.OK);
		else
			return new ResponseEntity<>(new ResponseHandler(true, "ProductCategories not found", productCategories), HttpStatus.OK);
	}
 
}
