package com.kirandeep.ecommerce.customer.service;

import org.springframework.stereotype.Service;

import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.repository.CustomerRepository;



@Service
public class CustomerService {

private com.kirandeep.ecommerce.customer.repository.CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository){
		this.customerRepository=customerRepository;
	}
	
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer findCustomerById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}
}
