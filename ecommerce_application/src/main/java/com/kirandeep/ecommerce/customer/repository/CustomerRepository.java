package com.kirandeep.ecommerce.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirandeep.ecommerce.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
