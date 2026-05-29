package com.kirandeep.ecommerce.authentication.security;

import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.repository.CustomerRepository;
import com.kirandeep.ecommerce.exception.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    private final CustomerRepository customerRepository;

    public AuthUtil(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getAuthenticatedCustomer(){

       Authentication authentication =
               SecurityContextHolder.getContext().getAuthentication();
       String email  = authentication.getName();
      Customer customer =
              customerRepository
                      .findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer Not found with email: "+email));

      return customer;
    }
}
