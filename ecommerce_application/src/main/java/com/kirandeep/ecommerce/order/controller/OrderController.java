package com.kirandeep.ecommerce.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirandeep.ecommerce.order.dto.OrderDto;
import com.kirandeep.ecommerce.order.dto.PlaceOrderRequestDto;
import com.kirandeep.ecommerce.order.service.OrderService;
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
	private OrderService orderService;
	
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> createOrder(@RequestBody PlaceOrderRequestDto purchaseDto) {
		OrderDto orderDto = orderService.createOrder(purchaseDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
		
	}
	
	@GetMapping("/{orderId}")
	public OrderDto getOrderDetails(@PathVariable Long orderId) {
		return this.orderService.getOrderDetails(orderId);
	}
}
