package com.pratibha.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.Customer;
import com.pratibha.ecommerce.requestresponse.ResponseHandler;
import com.pratibha.ecommerce.service.CustomerService;

@RestController
@RequestMapping("/ecommerce")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/byToken")
	public ResponseEntity<ResponseHandler> getCustomerByToken(){
		Customer customer = customerService.getCustomerByToken();
		return ResponseEntity.ok(new ResponseHandler(true, "Customer found", customer));
		
	}

}
