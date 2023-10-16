package com.pratibha.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratibha.ecommerce.entity.Cart;
import com.pratibha.ecommerce.entity.CartItem;
import com.pratibha.ecommerce.entity.Product;
import com.pratibha.ecommerce.repository.CartItemRepository;
import com.pratibha.ecommerce.repository.CartRepository;
import com.pratibha.ecommerce.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	public List<CartItem> getCartItemsByUserId(Integer userId) {
		return cartItemRepository.getCartItemsByUserId(userId);
	}

	public CartItem addToCart(Integer productId, Integer userId, Integer qty) {
		if (productId != null && userId != null) {
			CartItem cartItem;
			CartItem result;

			Product product = productRepository.findById(productId).get();

			Cart cart = cartRepository.findCartByUserId(userId);

			CartItem cartExisting = cartItemRepository.getCartItemsByUserIdAndProductId(userId, productId);

			if (cartExisting == null) {
				cartItem = new CartItem();
				cartItem.setDiscount((float) 10);
				cartItem.setPrice(product.getPrice());
				cartItem.setQuantity(qty != null ? qty : 1);
				cartItem.setProduct(product);
				cartItem.setCart(cart);
			} else {
				cartItem = cartExisting;
				cartItem.setQuantity(qty != null ? cartExisting.getQuantity() + qty : cartExisting.getQuantity() + 1);
			}

			result = cartItemRepository.save(cartItem);
			return result;
		} else {
			System.out.println("Produt Id/ user Id null");
			return null;
		}
	}

	public String DecreaseIncreaseCartItemQty(Integer cartProductId, Integer qty) {
		if (cartProductId == null || qty == null)
			return "cartProductId is null";
		try {
			CartItem existingCartItem = cartItemRepository.findById(cartProductId).get();

			if (qty <= 0) {
				cartItemRepository.deleteById(cartProductId);
			} else {
				existingCartItem.setQuantity(qty);
				cartItemRepository.save(existingCartItem);
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error while increasing decreasing cart item quantity";
		}
	}

	public String deleteCartItem(Integer cartProductId) {
		try {
		if (cartItemRepository.existsById(cartProductId)) {
			cartItemRepository.deleteById(cartProductId);
			return "success";
		} else {
			return "No cart item exists with id : " + cartProductId;
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Failed to delete cart item";
		}
	}
}
