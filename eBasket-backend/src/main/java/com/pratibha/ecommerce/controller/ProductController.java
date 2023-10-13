package com.pratibha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.Product;
import com.pratibha.ecommerce.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer productId) {
		Product product = productService.getProductById(productId);
		
		if (product != null) {
	        return ResponseEntity.ok(product);
	    }
	    else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/products/all")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name="searchKey", defaultValue="") String searchKey) {
		List<Product> products = null;
		
		if(searchKey == null || searchKey == "") {
		  products = productService.getAllProducts();
		}
		else {
		  products = productService.getProductsUsingSearchKey(searchKey);
		}
		
		if (products != null) {
	        return ResponseEntity.ok(products);
	    }
	    else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathParam("category") String category, @RequestParam(name="searchKey", defaultValue="") String searchKey) {
		List<Product> products = null;
		if(searchKey == null || searchKey == "") {
		 products = productService.getProductsByCategory(category);
		}
		else {
			  products = productService.getProductsUsingCategoryAndSearchKey(category, searchKey);
		}
		
		if (products != null) {
	        return ResponseEntity.ok(products);
	    }
	    else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
