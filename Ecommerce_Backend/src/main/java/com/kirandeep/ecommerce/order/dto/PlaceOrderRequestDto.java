package com.kirandeep.ecommerce.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequestDto {

	private String customerEmail;
	
	private Double totalPrice;
	
	private Integer totalQuantity;
	
	private List<OrderItemDto> orderItems;

	private Long addressId;

}
