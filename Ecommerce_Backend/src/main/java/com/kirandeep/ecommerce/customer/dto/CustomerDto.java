package com.kirandeep.ecommerce.customer.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {
    private String email;
    private String name;
    private String phoneNumber;
    private List<AddressDto> adresses;
}
