package com.kirandeep.ecommerce.customer.controller;

import com.kirandeep.ecommerce.authentication.security.AuthUtil;
import com.kirandeep.ecommerce.customer.dto.AddressDto;
import com.kirandeep.ecommerce.customer.dto.CustomerDto;
import com.kirandeep.ecommerce.customer.service.AddressService;
import com.kirandeep.ecommerce.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;
	private final AddressService addressService;
	private final AuthUtil authUtil;


	@PostMapping()
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer =  customerService.createCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
				}
	
	@GetMapping("/profile")
	public ResponseEntity<CustomerDto> getCustomerProfile() {
		CustomerDto customerDto = customerService.getCustomerProfile();
		return ResponseEntity.ok(customerDto);
	}


	@PostMapping("/add-address")
	public ResponseEntity<Map<String,String>> addAddress(@RequestBody  AddressDto addressDto){
		System.out.println("Entered Controller");
		addressService.saveAddress(addressDto);
		Map<String,String> message = new HashMap<>();
		message.put("message","Address Added Successfully");
		System.out.println(message);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(message);
	}

	@GetMapping("/addresses-list")
	public ResponseEntity<List<AddressDto>> addressList(){
		List<AddressDto> listOfAddressesDto =
				addressService.getAllCustomerAddresses();

		return ResponseEntity.status(HttpStatus.OK).body(listOfAddressesDto);
	}


	@GetMapping("/getAllOrders")
	public ResponseEntity<List<OrderDto>> getAllCustomerOrders(){
		List<OrderDto> ordersList =
				customerService.getAllCustomerOrders();
		return ResponseEntity.status(HttpStatus.OK).body(ordersList);
	}
	
}
