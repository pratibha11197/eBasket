package com.pratibha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratibha.ecommerce.entity.OrderItem;
import com.pratibha.ecommerce.entity.Orders;
import com.pratibha.ecommerce.requestresponse.ResponseHandler;
import com.pratibha.ecommerce.service.OrderService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/ecommerce")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
     
	@PostMapping("/order")
	public ResponseEntity<ResponseHandler> placeOrder(@PathParam(value = "userId") Integer userId, @PathParam(value = "cartId") Integer cartId){
	
		if(userId == null || userId == 0 || cartId == null || cartId == 0) {
			return ResponseEntity.ok(new ResponseHandler(true, "User Id or Cart Id is null.", null));
		}
		
		try {
		    orderService.placeOrder(userId, cartId);
		}
		catch(Exception e) {
			return ResponseEntity.ok(new ResponseHandler(false, "Failed to place Order.", null));
		}
		
		return ResponseEntity.ok(new ResponseHandler(true, "Order Placed successfully.", null));
	}
	
	@GetMapping("/orders/{userId}")
	public ResponseEntity<ResponseHandler> getAllOrders(@PathVariable("userId") Integer userId){
		if(userId == null || userId == 0) {
			return ResponseEntity.ok(new ResponseHandler(true, "User Id is null.", null));
		}
		
		List<Orders> orders = orderService.getAllOrders(userId);
		
		return ResponseEntity.ok(new ResponseHandler(true, "Customer found", orders));
		
	}
	
	@GetMapping("/orders/id/{orderId}")
	public ResponseEntity<ResponseHandler> getOrderById(@PathVariable("orderId") Integer orderId){
		if(orderId == null || orderId == 0) {
			return ResponseEntity.ok(new ResponseHandler(true, "Order Id is null.", null));
		}
		
		Orders orders = orderService.getOrderById(orderId);
		
		return ResponseEntity.ok(new ResponseHandler(true, "Orders found", orders));
		
	}
	
	@GetMapping("/orders/items/{orderId}")
	public ResponseEntity<ResponseHandler> getOrderItemsById(@PathVariable("orderId") Integer orderId){
		if(orderId == null || orderId == 0) {
			return ResponseEntity.ok(new ResponseHandler(true, "Order Id is null.", null));
		}
		
		List<OrderItem> orderItems = orderService.getOrderItemsById(orderId);
		
		return ResponseEntity.ok(new ResponseHandler(true, "Orders Items found", orderItems));
		
	}
	
}
