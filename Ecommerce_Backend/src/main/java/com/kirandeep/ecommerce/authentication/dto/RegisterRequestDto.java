package com.kirandeep.ecommerce.authentication.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
private String email;
private String name;
private String phoneNumber;
private String password;
}
