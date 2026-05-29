package com.kirandeep.ecommerce.customer.repository;

import com.kirandeep.ecommerce.customer.entity.Address;
import com.kirandeep.ecommerce.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
List<Address> findByCustomer(Customer customer);
}
