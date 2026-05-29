package com.kirandeep.ecommerce.order.controller;

import com.kirandeep.ecommerce.order.dto.OrderResponseDto;
import com.kirandeep.ecommerce.order.dto.PaymentResponseDto;
import com.razorpay.RazorpayException;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")

public class OrderController {

	private OrderService orderService;

	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<OrderResponseDto> createOrder(@RequestBody PlaceOrderRequestDto purchaseDto) throws RazorpayException {
		System.out.println("controller reached");
		OrderResponseDto orderResponseDto = orderService.createOrder(purchaseDto);
		System.out.println(orderResponseDto);
		//return ResponseEntity.ok("TEST");
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
		
	}

	@PostMapping("/verifyPayment")
	public ResponseEntity<Map<String,String>> verifyPaymentAndOrderStatus(@RequestBody PaymentResponseDto paymentResponseDto){
		Boolean isOrderVerified = orderService.
				verifyPayment(paymentResponseDto);

		if(isOrderVerified){
			Map<String,String> response = new HashMap<>();
			response.put("message","Payment Verified Successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else{
			Map<String,String> response = new HashMap<>();
			response.put("message","Unable to Verify payment");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}


	}
	
	@GetMapping("/{orderId}")
	public OrderDto getOrderDetails(@PathVariable Long orderId) {

		return this.orderService.getOrderDetails(orderId);
	}
}
