package com.kirandeep.ecommerce.order.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 private Double price;
	 
	 private Integer quantity;
	 
	 private Long productId;
	 
	 private String productName;
	 @ManyToOne
	 @JoinColumn(name="order_id")
	 private Order order;

	 
	 
}
