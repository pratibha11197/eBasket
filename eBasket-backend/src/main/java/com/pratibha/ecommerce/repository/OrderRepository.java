package com.pratibha.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pratibha.ecommerce.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

	
	@Query("select o from Orders o where o.customer.customer_id = :userId")
	List<Orders> getAllOrdersByUserId(Integer userId);
	
	

}
