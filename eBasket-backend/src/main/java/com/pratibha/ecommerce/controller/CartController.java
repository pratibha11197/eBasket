package com.pratibha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.CartItem;
import com.pratibha.ecommerce.service.CartService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/ecommerce")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<List<CartItem>> getCartItemsByUserId(@PathVariable("userId") Integer userId){
		
		List<CartItem> cartItems = cartService.getCartItemsByUserId(userId);
		
		if (cartItems != null) {
	        return ResponseEntity.ok(cartItems);
	    }
	    else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/cart/add")
	public ResponseEntity<Object> AddProductToCart(@PathParam("productId") Integer productId, @PathParam("userId") Integer userId, @RequestParam(name="qty", required=false) Integer qty) {
		CartItem cartItem = cartService.addToCart(productId, userId, qty);
		return ResponseEntity.ok(cartItem);
	}

	@GetMapping("/cart/incDesCartItemQty")
	public ResponseEntity<Object> DecreaseIncreaseCartItemQty(@RequestParam("cartProductId") Integer cartProductId, @RequestParam("qty") Integer qty) {
		String res = cartService.DecreaseIncreaseCartItemQty(cartProductId, qty);
		return ResponseEntity.ok(null);
	}

	@DeleteMapping("/cart/{cartProductId}")
	public void deleteCartItem(@PathVariable("cartProductId") Integer cartProductId){
		if(cartProductId != null) {
		   cartService.deleteCartItem(cartProductId);
		}
	}
	
}
