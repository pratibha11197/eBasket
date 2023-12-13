package com.pratibha.ecommerce.controller;

import java.math.BigInteger;

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

import jakarta.websocket.server.PathParam;

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
	
	@PostMapping("/customer/edit")
	public ResponseEntity<ResponseHandler> editCustomerDetails(@PathParam(value = "userId") Integer userId, @PathParam(value = "userName") String userName, @PathParam(value = "email") String email, @PathParam(value = "phone") String phone){
	
		if(userId == null || userId == 0 || userName == null || userName == "" || email == null || email == "" || phone == null) {
			return ResponseEntity.ok(new ResponseHandler(true, "User details field is/ are null.", null));
		}
		
		try {
			Customer customer =  customerService.editCustomerDetails(userId, userName, email, phone);
		  return ResponseEntity.ok(new ResponseHandler(true, "User Details updated successfully successfully.", customer));
		}
		catch(Exception e) {
			return ResponseEntity.ok(new ResponseHandler(false, "Failed to update user details.", null));
		}	
	}
}
