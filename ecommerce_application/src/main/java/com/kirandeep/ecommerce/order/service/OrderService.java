package com.kirandeep.ecommerce.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.repository.CustomerRepository;
import com.kirandeep.ecommerce.order.dto.OrderDto;
import com.kirandeep.ecommerce.order.dto.OrderItemDto;
import com.kirandeep.ecommerce.order.dto.PlaceOrderRequestDto;
import com.kirandeep.ecommerce.order.entity.Order;
import com.kirandeep.ecommerce.order.entity.OrderItem;
import com.kirandeep.ecommerce.order.entity.OrderStatus;
import com.kirandeep.ecommerce.order.repository.OrderRepository;
import com.kirandeep.ecommerce.exception.*;
@Service
public class OrderService {




	private OrderRepository orderRepository;
	private CustomerRepository customerRepository;
	
	
	

	public OrderService(OrderRepository orderRepository,CustomerRepository customerRepository) {
		this.orderRepository=orderRepository;
		this.customerRepository=customerRepository;

	}
	
	public OrderDto createOrder(PlaceOrderRequestDto placeOrderRequestDto) {
		
		Integer totalQuantity=0;
		
		Double totalPrice=0.0;
		
		Order order = null;
		OrderDto orderDto = null;
		List<OrderItemDto> orderItemsDto = placeOrderRequestDto.getOrderItems();
		List<OrderItem> orderItems = new ArrayList<>();
		Customer customer = customerRepository.findById(placeOrderRequestDto.getCustomerId()).orElseThrow( ()->
				new ResourceNotFoundException("Customer Not Found with the ID"+placeOrderRequestDto.getCustomerId()));
		if(customer!=null) {
			
			order = new Order();
		

			for(OrderItemDto orderItem : orderItemsDto) {
				
				 totalQuantity = totalQuantity + orderItem.getQuantity();
				 totalPrice = totalPrice+orderItem.getPrice();
				 
				
			
			
			
			OrderItem item = new OrderItem();
			item.setPrice(orderItem.getPrice());
			item.setProductId(orderItem.getProductId());
			item.setOrder(order);
			
			orderItems.add(item);
			}
			
			order.setCustomer(customer);
			order.setTotalPrice(totalPrice);
			order.setTotalQuantity(totalQuantity);
			order.setOrderItems(orderItems);
			order.setOrderStatus(OrderStatus.CONFIRMED);
			orderRepository.save(order);
			
			
			
			orderDto = new OrderDto();
			orderDto.setOrderId(order.getOrderId());
		orderDto.setTotalPrice(totalPrice);
			orderDto.setTotalQuantity(totalQuantity);
			orderDto.setOrderItems(orderItemsDto);
			orderDto.setOrderStatus(order.getOrderStatus().toString());
			orderDto.setCustomerId(order.getCustomer().getId());
			orderDto.setCreatedAt(order.getCreatedAt());
			
		
			

			
		}
		return orderDto;
		
		
		
		
	}
	
	public OrderDto getOrderDetails(Long orderId) {
		Order order = this.orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not found"));
		
		
		
		
		List<OrderItemDto> orderItems = new ArrayList<>();
order.getOrderItems().forEach(item->{
	OrderItemDto orderItemDto = new OrderItemDto();
	orderItemDto.setPrice(item.getPrice());
	orderItemDto.setProductId(item.getProductId());
	orderItemDto.setQuantity(item.getQuantity());
	orderItems.add(orderItemDto);
});
		
		OrderDto orderDto = new OrderDto();
		
		orderDto.setOrderId(order.getOrderId());
		orderDto.setCustomerId(order.getCustomer().getId());
		orderDto.setOrderItems(orderItems);
		orderDto.setOrderStatus(order.getOrderStatus().toString());
		orderDto.setCreatedAt(order.getCreatedAt());
		orderDto.setTotalPrice(order.getTotalPrice());
		orderDto.setTotalQuantity(order.getTotalQuantity());
		
		return orderDto;
		
	}
	
	
	
}
