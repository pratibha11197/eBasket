package com.pratibha.ecommerce.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private Integer cart_id;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems;
	
	@OneToOne(mappedBy="cart")
	private Customer customer;

	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

//	public List<CartItem> getCartItems() {
//		return cartItems;
//	}
//
//	public void setCartItems(List<CartItem> cartItems) {
//		this.cartItems = cartItems;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
	
}
