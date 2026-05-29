package com.kirandeep.ecommerce.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.kirandeep.ecommerce.customer.entity.Address;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.kirandeep.ecommerce.customer.entity.Customer;

@Entity
@Table (name="orders")
@Data
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private Double totalPrice;
	
	private Integer totalQuantity;

	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.PENDING;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	private List<OrderItem> orderItems;

	@Column(name="razorpay_order_id")
	private String razorpayOrderId;
	@Column(name="payment_id")
	private String paymentId;

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	private String pincode;
	private String state;
	private String street;
	private String city;

}
