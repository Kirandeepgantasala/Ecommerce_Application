package com.kirandeep.ecommerce.customer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String phoneNumber;
    private String houseNumber;
    private String street;
    private String state;
    private String city;
    private String pincode;
    private String country;
    private String landmark;

    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;
}
