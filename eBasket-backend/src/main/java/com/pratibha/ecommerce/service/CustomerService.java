package com.pratibha.ecommerce.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pratibha.ecommerce.config.JwtUtil;
import com.pratibha.ecommerce.entity.Cart;
import com.pratibha.ecommerce.entity.Customer;
import com.pratibha.ecommerce.entity.Role;
import com.pratibha.ecommerce.repository.CartRepository;
import com.pratibha.ecommerce.repository.CustomerRepository;
import com.pratibha.ecommerce.requestresponse.AuthenticationRequest;
import com.pratibha.ecommerce.requestresponse.AuthenticationResponse;
import com.pratibha.ecommerce.requestresponse.ResponseHandler;

@Service
public class CustomerService {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManagerBean;

	public Customer getCustomerByEmail(String email) {
		return customerRepository.findCustomerByEmail(email) ;
	}

	public ResponseHandler registerCustomer(Customer customer) {
		
		try {
		Customer newCustomer = new Customer();
		newCustomer.setCustomer_name(customer.getCustomer_name());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setAddress(customer.getAddress());
		newCustomer.setPhone_no((BigInteger) customer.getPhone_no());
		newCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
		newCustomer.setRole(Role.USER);
		Cart newCustomeCart = new Cart();
		Cart createdCart = cartRepository.save(newCustomeCart);
		
		newCustomer.setCart(createdCart);
		
		Customer registeredCustomer = customerRepository.save(newCustomer);
		
		return new ResponseHandler(true, "Customer Registered successfully", registeredCustomer);
		
		}
		catch(Exception e) {
			
			return new ResponseHandler(false, "Failed to register customer", null);
			
		}
	}

	public AuthenticationResponse login(AuthenticationRequest request) {
		
		authenticationManagerBean.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
				);
		
		var user = customerRepository.findCustomerByEmail(request.getEmail()); 
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(jwtUtil.generateToken(user));
		
		return response;
	}
	
     public Customer getCustomerByToken() {
    	 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         Customer user = customerRepository.findCustomerByEmail(((UserDetails) principal).getUsername());
         return user;
	}

}
