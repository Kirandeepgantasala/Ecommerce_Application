package com.kirandeep.ecommerce.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kirandeep.ecommerce.config.RazorpayConfig;
import com.kirandeep.ecommerce.customer.entity.Address;
import com.kirandeep.ecommerce.customer.repository.AddressRepository;
import com.kirandeep.ecommerce.order.dto.*;
import com.kirandeep.ecommerce.order.utils.RazorpaySignatureUtil;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.repository.CustomerRepository;
import com.kirandeep.ecommerce.order.entity.Order;
import com.kirandeep.ecommerce.order.entity.OrderItem;
import com.kirandeep.ecommerce.order.entity.OrderStatus;
import com.kirandeep.ecommerce.order.repository.OrderRepository;
import com.kirandeep.ecommerce.exception.*;
@Service
@RequiredArgsConstructor
public class OrderService {




	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final RazorpayClient razorpayClient;
	private final RazorpayConfig razorpayConfig;
	private final AddressRepository addressRepository;


	@Transactional
	public OrderResponseDto createOrder(PlaceOrderRequestDto placeOrderRequestDto) throws RazorpayException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		Integer totalQuantity=0;
		Double totalPrice=0.0;

		List<OrderItemDto> orderItemsDto = placeOrderRequestDto.getOrderItems();
		totalQuantity = orderItemsDto
				.stream()
				.mapToInt(item -> item.getQuantity()).sum();

		totalPrice = orderItemsDto
				.stream()
				.mapToDouble(item-> item.getQuantity() * item.getPrice())
				.sum();



		Customer customer = customerRepository.findByEmail(email).orElseThrow( ()->
				new ResourceNotFoundException("Customer Not Found with the email: "+email));

		Address address = addressRepository.findById(placeOrderRequestDto.getAddressId())
				.orElseThrow(()-> new RuntimeException("Address Not Found"));


		if(!address.getCustomer().getId().equals(customer.getId())){
			throw new RuntimeException("Unauthorized Address");

		}

Integer amount = (int) (totalPrice*100);
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",amount);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt","Rec_1");


			com.razorpay.Order razorpayOrder = razorpayClient.orders.create(orderRequest);

			String razorpayOrderId = razorpayOrder.get("id");


Order order = new Order();

		List<OrderItem> orderItems = orderItemsDto
				.stream()
				.map(orderItemDto -> {
					OrderItem orderItem = new OrderItem();
					orderItem.setPrice(orderItemDto.getPrice());
					orderItem.setQuantity(orderItemDto.getQuantity());
					orderItem.setProductId(orderItemDto.getProductId());
					orderItem.setProductName(orderItemDto.getProductName());
					orderItem.setOrder(order);
					return orderItem;
				})
				.collect(Collectors.toCollection((ArrayList::new)));


			order.setCustomer(customer);
			order.setTotalPrice(totalPrice);
			order.setTotalQuantity(totalQuantity);
			order.setOrderItems(orderItems);
			order.setOrderStatus(OrderStatus.PENDING);
			order.setRazorpayOrderId(razorpayOrderId);
			order.setAddress(address);
			order.setCity(address.getCity());
			order.setState(address.getState());
			order.setPincode(address.getPincode());
			order.setStreet(address.getStreet());
			Order savedOrder = orderRepository.save(order);


		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setRazorpayOrderId(savedOrder.getRazorpayOrderId());
		orderResponseDto.setCurrency("INR");
		orderResponseDto.setAmount(savedOrder.getTotalPrice());
		orderResponseDto.setKey(razorpayConfig.getKey());

		return orderResponseDto;
		
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


	public Boolean verifyPayment(PaymentResponseDto paymentResponseDto){

		String payload = paymentResponseDto.getRazorpayOrderId()+"|"+paymentResponseDto.getRazorpayPaymentId();
		boolean isVerified = RazorpaySignatureUtil.verifySignature(payload,paymentResponseDto.getRazorpaySignature(),razorpayConfig.getSecret());


		if(isVerified){
		Order order =
				orderRepository
						.findByRazorpayOrderId(paymentResponseDto.getRazorpayOrderId())
								.orElseThrow(()->new RuntimeException("Razorpay order Id not found with ID: "+paymentResponseDto.getRazorpayOrderId()));

		order.setPaymentId(paymentResponseDto.getRazorpayPaymentId());
		order.setOrderStatus(OrderStatus.CONFIRMED);

		orderRepository.save(order);

			return true;
		}

		return false;
	}


	
	
	
}
