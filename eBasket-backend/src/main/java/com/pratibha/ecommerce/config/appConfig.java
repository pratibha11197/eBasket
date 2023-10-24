package com.pratibha.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pratibha.ecommerce.service.JwtUserDetailsService;

@Configuration
public class appConfig {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean 
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(jwtUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		
		return authProvider;
	}
}
