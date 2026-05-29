package com.kirandeep.ecommerce.customer.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String customerName;
    private String houseNumber;
    private String phoneNumber;
    private String street;
    private String state;
    private String city;
    private String pincode;
    private String country;
    private String landmark;
}
