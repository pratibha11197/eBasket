package com.pratibha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pratibha.ecommerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	
	@Query("select c from Cart c where c.customer.customer_id=:userId")
	Cart findCartByUserId(Integer userId);

}
