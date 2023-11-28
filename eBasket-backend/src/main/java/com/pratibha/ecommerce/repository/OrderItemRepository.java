package com.pratibha.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratibha.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	@Query("select o from OrderItem o where o.orders.order_id = :orderId")
	List<OrderItem> findAllByOrderId(Integer orderId);

}
