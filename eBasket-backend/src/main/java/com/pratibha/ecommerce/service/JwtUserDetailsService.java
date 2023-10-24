package com.pratibha.ecommerce.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pratibha.ecommerce.entity.Customer;
import com.pratibha.ecommerce.repository.CustomerRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository repo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = repo.findCustomerByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with the username of: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

//    public Customer save (Customer user) {
//    	Customer newUser = new Customer(user.getCustomer_id(), user.getCustomer_name(), user.getEmail(), bcryptEncoder.encode(user.getPassword()),
//                 user.getAddress(), user.getPhone_no());
//
//        return repo.save(newUser);
//    }
}