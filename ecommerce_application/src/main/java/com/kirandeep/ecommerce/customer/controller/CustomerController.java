package com.kirandeep.ecommerce.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.service.CustomerService;


@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		
		this.customerService=customerService;
	}
	
	@PostMapping()
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer =  customerService.createCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
				}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
	
		Customer customer = customerService.findCustomerById(id);
		return ResponseEntity.ok(customer); 
	}
	
}
