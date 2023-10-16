package com.pratibha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.Product;
import com.pratibha.ecommerce.requestresponse.ResponseHandler;
import com.pratibha.ecommerce.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseHandler> getProductById(@PathVariable("id") Integer productId) {
		Product product = productService.getProductById(productId);
		
		if (product != null) {
	        return new ResponseEntity<>(new ResponseHandler(true, "OK", product), HttpStatus.OK);
	    }
	    else {
	    	return new ResponseEntity<>(new ResponseHandler(false, "Product not found with Id " + productId, product), HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/products/all")
	public ResponseEntity<ResponseHandler> getAllProducts(@RequestParam(name="searchKey", defaultValue="") String searchKey) {
		List<Product> products = null;
		
		if(searchKey == null || searchKey.isEmpty()) {
		  products = productService.getAllProducts();
		}
		else {
		  products = productService.getProductsUsingSearchKey(searchKey);
		}
		
		if (products != null && products.size() > 0) {
			return new ResponseEntity<>(new ResponseHandler(true, "OK", products), HttpStatus.OK);
	    }
	    else {
	    	return new ResponseEntity<>(new ResponseHandler(false, "No product found", products), HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseHandler> getProductsByCategory(@PathParam("category") String category, @RequestParam(name="searchKey", defaultValue="") String searchKey) {
		List<Product> products = null;
		if(searchKey == null || searchKey.isEmpty()) {
		  products = productService.getProductsByCategory(category);
		}
		else {
			  products = productService.getProductsUsingCategoryAndSearchKey(category, searchKey);
		}
		
		if (products != null) {
			return new ResponseEntity<>(new ResponseHandler(true, "OK", products), HttpStatus.OK);
	    }
	    else {
	    	return new ResponseEntity<>(new ResponseHandler(false, "No product found", products), HttpStatus.NOT_FOUND);
	    }
	}
}
