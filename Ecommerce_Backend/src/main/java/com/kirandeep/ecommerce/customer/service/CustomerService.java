package com.kirandeep.ecommerce.customer.service;

import com.kirandeep.ecommerce.authentication.security.AuthUtil;
import com.kirandeep.ecommerce.customer.dto.CustomerDto;
import com.kirandeep.ecommerce.exception.ResourceNotFoundException;
import com.kirandeep.ecommerce.order.dto.OrderDto;
import com.kirandeep.ecommerce.order.dto.OrderItemDto;
import com.kirandeep.ecommerce.order.entity.Order;
import com.kirandeep.ecommerce.order.repository.OrderRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.repository.CustomerRepository;

import java.util.List;


@Service
public class CustomerService {

	private final AuthenticationManager authenticationManager;
	private final AuthUtil authUtil;
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	
	public CustomerService(CustomerRepository customerRepository, AuthenticationManager authenticationManager, AuthUtil authUtil, OrderRepository orderRepository){
		this.customerRepository=customerRepository;
		this.authenticationManager = authenticationManager;
		this.authUtil = authUtil;
        this.orderRepository = orderRepository;
    }
	
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer findCustomerById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	public CustomerDto getCustomerProfile(){
		Customer customer = authUtil.getAuthenticatedCustomer();
			CustomerDto customerDto = new CustomerDto();
			customerDto.setEmail(customer.getEmail());
			customerDto.setName(customer.getName());
			customerDto.setPhoneNumber(customer.getPhoneNumber());
			return customerDto;
	}

	public List<OrderDto> getAllCustomerOrders(){

		Customer customer =
				authUtil.getAuthenticatedCustomer();

		List<Order> ordersList =
				orderRepository.findByCustomerOrderByCreatedAtDesc(customer);

return ordersList.stream().map(order -> {
			OrderDto dto = new OrderDto();
			dto.setOrderId(order.getOrderId());
			dto.setOrderItems(order.getOrderItems().stream().map(orderItem -> {
				OrderItemDto orderItemDto = new OrderItemDto();
				orderItemDto.setProductName(orderItem.getProductName());
				orderItemDto.setProductId(orderItem.getProductId());
				orderItemDto.setQuantity(orderItem.getQuantity());
				orderItemDto.setPrice(orderItem.getPrice());
				return orderItemDto;
			}).toList());
			dto.setCustomerId(order.getCustomer().getId());
			dto.setOrderStatus(String.valueOf(order.getOrderStatus()));
			dto.setCreatedAt(order.getCreatedAt());
			dto.setTotalPrice(order.getTotalPrice());
			dto.setTotalQuantity(order.getTotalQuantity());

			return dto;


		}).toList();


	}


}
