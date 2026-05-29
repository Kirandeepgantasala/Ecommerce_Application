package com.kirandeep.ecommerce.authentication.dto;

import com.kirandeep.ecommerce.authentication.enums.Role;
import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String email;
    private Role role;
}
