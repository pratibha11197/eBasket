package com.pratibha.ecommerce.entity;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer order_id;
	
	@Column(name="order_no")
	private Integer order_no;
	
	@Column(name="order_status")
	private OrderStatus order_status;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@JsonIgnore
	@OneToMany(mappedBy = "orders")
	private List<OrderItem> orderItem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_date")
	private Date order_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ship_date")
	private Date ship_date;
	
	@Column(name="delivery_slot")
	private String delivery_slot;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_date")
	private Date delivery_date;
	
	@Column(name="total_price")
	private Double total_price;
	
	@Column(name="total_item_qty")
	private Integer total_item_qty;
	
	@Column(name="order_address")
	private String order_address;
	
	@Column(name="order_phone_no")
	private BigInteger order_phone_no;
	
	@Column(name="order_mail")
	private String order_mail;
	
	@Column(name="payment_status")
	private boolean payment_status;
	
	@Column(name="payment_id")
	private Integer payment_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date")
	private Date payment_date;
	
	@Column(name="paid_amount")
	private Float paid_amount;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OrderStatus orderPlaced) {
		this.order_status = orderPlaced;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Date getShip_date() {
		return ship_date;
	}

	public void setShip_date(Date ship_date) {
		this.ship_date = ship_date;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price2) {
		this.total_price = total_price2;
	}

	public Integer getTotal_item_qty() {
		return total_item_qty;
	}

	public void setTotal_item_qty(Integer total_item_qty) {
		this.total_item_qty = total_item_qty;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public BigInteger getOrder_phone_no() {
		return order_phone_no;
	}

	public void setOrder_phone_no(BigInteger bigInteger) {
		this.order_phone_no = bigInteger;
	}

	public String getOrder_mail() {
		return order_mail;
	}

	public void setOrder_mail(String order_mail) {
		this.order_mail = order_mail;
	}

	public boolean isPayment_status() {
		return payment_status;
	}

	public void setPayment_status(boolean payment_status) {
		this.payment_status = payment_status;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public Float getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(Float paid_amount) {
		this.paid_amount = paid_amount;
	}

	public String getDelivery_slot() {
		return delivery_slot;
	}

	public void setDelivery_slot(String delivery_slot) {
		this.delivery_slot = delivery_slot;
	}
	
	
}

