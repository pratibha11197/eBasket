package com.pratibha.ecommerce.entity;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer customer_id;
	
	@Column(name = "customer_name")
	private String customer_name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_no")
	private BigInteger phone_no;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
	private Cart cart;

	@Enumerated(EnumType.STRING)
	private Role role;

	public Customer() {
	}

	public Customer(Integer customer_id, String customer_name, String email, String password, String address,
			BigInteger phone_no, Cart cart) {
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone_no = phone_no;
		this.cart = cart;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(BigInteger phone_no) {
		this.phone_no = phone_no;
	}

//	public Cart getCart() {
//		return cart;
//	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return List.of(new SimpleGrantedAuthority(role.name()));
}

@Override
public String getUsername() {
	return email;
}

@Override
public boolean isAccountNonExpired() {
	return true;
}

@Override
public boolean isAccountNonLocked() {
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	return true;
}

@Override
public boolean isEnabled() {
	return true;
}

}
