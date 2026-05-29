package com.kirandeep.ecommerce.order.repository;

import com.kirandeep.ecommerce.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirandeep.ecommerce.order.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByRazorpayOrderId(String razorpayOrderId);

    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

}
