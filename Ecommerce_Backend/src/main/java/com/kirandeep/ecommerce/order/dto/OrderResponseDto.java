package com.kirandeep.ecommerce.order.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private String razorpayOrderId;

    private String key;
    private Double amount;
    private String currency;
}
