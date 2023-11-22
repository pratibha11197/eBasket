package com.pratibha.ecommerce.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pratibha.ecommerce.entity.Cart;
import com.pratibha.ecommerce.entity.CartItem;
import com.pratibha.ecommerce.entity.Customer;
import com.pratibha.ecommerce.entity.OrderItem;
import com.pratibha.ecommerce.entity.OrderStatus;
import com.pratibha.ecommerce.entity.Orders;
import com.pratibha.ecommerce.exception.EBasketException;
import com.pratibha.ecommerce.repository.CartItemRepository;
import com.pratibha.ecommerce.repository.CartRepository;
import com.pratibha.ecommerce.repository.CustomerRepository;
import com.pratibha.ecommerce.repository.OrderItemRepository;
import com.pratibha.ecommerce.repository.OrderRepository;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	Random rand = new Random();
	
	
	@Transactional
	public void placeOrder(Integer userId, Integer cartId) {	    
	    
	    Customer user = customerRepository.findById(userId).get();
	    Cart cart = cartRepository.findById(cartId).get();
	    try {
	    if(user == null || cart == null) {
	    	throw new EBasketException("User/ Cart not found");
	    }
	    
	    List<CartItem> cartItems = cartItemRepository.getCartItemsByUserId(userId);
	    
	    if(cartItems.size() <= 0){
	    	throw new EBasketException("No items found in cart");
	    }
	    
	    Orders newOrders = new Orders();
	    Integer total_items = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        Double total_price = cartItems.stream().mapToDouble(i -> i.getQuantity()*i.getPrice()).sum();
	    
		 // place order
         newOrders.setOrder_no(rand.nextInt(1000));
		 newOrders.setOrder_status(OrderStatus.Order_Placed);
		 newOrders.setCustomer(user);
		 newOrders.setOrder_date(new Date());
		 newOrders.setShip_date(new Date());
		 newOrders.setDelivery_slot("13 Oct, Fri, Between 6:30 PM - 8:30 PM");
		 newOrders.setDelivery_date(new Date());
		 newOrders.setOrder_address(user.getAddress());
		 newOrders.setOrder_mail(user.getEmail());
		 newOrders.setOrder_phone_no(user.getPhone_no());
		 newOrders.setTotal_item_qty(total_items);
		 newOrders.setTotal_price(total_price);
	     newOrders.setPayment_status(true);
	     newOrders.setPaid_amount(10.0f);
	     newOrders.setPayment_date(new Date());
	     newOrders.setPayment_id(rand.nextInt());
		 
		 Orders placedOrder = orderRepository.saveAndFlush(newOrders);
		 
		 
		 List<OrderItem> orderItems = new ArrayList<>();
		 cartItems.forEach(x -> {
			 OrderItem item = new OrderItem();
			 item.setOrder(placedOrder);
			 item.setProduct(x.getProduct());
			 item.setQuantity(x.getQuantity());
			 orderItems.add(item);
		 });		 
		 
		 orderItemRepository.saveAll(orderItems);
		 
		// empty cart item of user if order success
		 cartItemRepository.deleteAllByCartId(cartId);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	public List<Orders> getAllOrders(Integer userId) {
		return this.orderRepository.getAllOrdersByUserId(userId);
	}
	
}
