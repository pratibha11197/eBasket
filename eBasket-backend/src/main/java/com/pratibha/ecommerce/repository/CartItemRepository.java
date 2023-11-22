package com.pratibha.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pratibha.ecommerce.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	@Query("Select c from CartItem c where c.cart.customer.customer_id=:userId")
	List<CartItem> getCartItemsByUserId(Integer userId);
	
	@Query("Select c from CartItem c where c.cart.customer.customer_id=:userId and c.product.product_id=:productId")
	CartItem getCartItemsByUserIdAndProductId(Integer userId, Integer productId);

	@Modifying
	@Query("delete from CartItem c where c.cart.cart_id=:cartId")
	void deleteAllByCartId(Integer cartId);
	

}
