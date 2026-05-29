package com.kirandeep.ecommerce.order.dto;

import lombok.Data;

@Data
public class OrderItemDto {


	private Long productId;
	
	private Integer quantity;
	private String productName;
	private Double price;
}
