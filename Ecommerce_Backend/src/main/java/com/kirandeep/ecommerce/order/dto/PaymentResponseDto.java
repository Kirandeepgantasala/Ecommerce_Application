package com.kirandeep.ecommerce.order.dto;

import lombok.Data;

@Data
public class PaymentResponseDto {

    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
}
