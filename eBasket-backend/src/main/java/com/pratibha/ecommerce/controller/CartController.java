package com.pratibha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.CartItem;
import com.pratibha.ecommerce.requestresponse.ResponseHandler;
import com.pratibha.ecommerce.service.CartService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/ecommerce")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<ResponseHandler> getCartItemsByUserId(@PathVariable("userId") Integer userId){
		
		List<CartItem> cartItems = cartService.getCartItemsByUserId(userId);
		
		if (cartItems != null && cartItems.size() > 0) {
			return new ResponseEntity<>(new ResponseHandler(true, "OK", cartItems), HttpStatus.OK);
	    }
	    else {
	    	return new ResponseEntity<>(new ResponseHandler(true, "No item in cart", cartItems), HttpStatus.OK);
	    }
	}
	
	@PostMapping("/cart/add")
	public ResponseEntity<ResponseHandler> AddProductToCart(@PathParam("productId") Integer productId, @PathParam("userId") Integer userId, @RequestParam(name="qty", required=false) Integer qty) {
		CartItem cartItem = cartService.addToCart(productId, userId, qty);
		
		if(cartItem != null) {
			return new ResponseEntity<>(new ResponseHandler(true, "OK", cartItem), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseHandler(false, "Failed to add to cart", cartItem), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cart/incDesCartItemQty")
	public ResponseEntity<ResponseHandler> DecreaseIncreaseCartItemQty(@RequestParam("cartProductId") Integer cartProductId, @RequestParam("qty") Integer qty) {
		String res = cartService.DecreaseIncreaseCartItemQty(cartProductId, qty);
		
		if(res == "success") {
			return new ResponseEntity<>(new ResponseHandler(true, "Item quantity Increased/Decresed", null), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseHandler(false, "Failed to Increased/Decresed cart item quantity.", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/cart/{cartProductId}")
	public ResponseEntity<ResponseHandler> deleteCartItem(@PathVariable("cartProductId") Integer cartProductId){
		if(cartProductId != null) {
			String res = cartService.deleteCartItem(cartProductId);
			if(res == "success")
				return new ResponseEntity<>(new ResponseHandler(true, "Item deleted from Cart.", null), HttpStatus.OK);
			else
				return new ResponseEntity<>(new ResponseHandler(false, "Failed to delete item from Cart.", null), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseHandler(true, "CartProductId is null", null), HttpStatus.OK);
		}
	}
}
