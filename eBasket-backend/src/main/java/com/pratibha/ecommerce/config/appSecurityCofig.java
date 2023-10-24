package com.pratibha.ecommerce.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class appSecurityCofig {

//    @Autowired
//    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//    }

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().configurationSource(new CorsConfigurationSource() {
		    @Override
		    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		        CorsConfiguration config = new CorsConfiguration();
		        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		        config.setAllowedMethods(Collections.singletonList("*"));
		        config.setAllowCredentials(true);
		        config.setAllowedHeaders(Collections.singletonList("*"));
		        config.setExposedHeaders(Arrays.asList("Authorization"));
		        config.setMaxAge(1200L);
		        return config;
		    }})
		.and()
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/ecommerce/auth/**", "/ecommerce/productCategory/**", "/ecommerce/product/**", "/ecommerce/products/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
}