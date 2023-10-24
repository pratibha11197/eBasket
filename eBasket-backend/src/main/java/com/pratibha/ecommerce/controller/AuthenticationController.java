package com.pratibha.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.Customer;
import com.pratibha.ecommerce.requestresponse.AuthenticationRequest;
import com.pratibha.ecommerce.requestresponse.AuthenticationResponse;
import com.pratibha.ecommerce.requestresponse.ResponseHandler;
import com.pratibha.ecommerce.service.CustomerService;

@RestController
@RequestMapping("/ecommerce/auth")
public class AuthenticationController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(customerService.login(request));
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<ResponseHandler> registerCustomer(@RequestBody Customer customer){
		
		if(customer == null)
			return new ResponseEntity<>(new ResponseHandler(false, "Customer data null", null), HttpStatus.OK);
		
		Customer existingCustomer = customerService.getCustomerByEmail(customer.getEmail());

		if(existingCustomer != null)
			return new ResponseEntity<>(new ResponseHandler(false, "Customer with email " + customer.getEmail() + " already exists.", null), HttpStatus.OK);
		
        ResponseHandler response = customerService.registerCustomer(customer);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
