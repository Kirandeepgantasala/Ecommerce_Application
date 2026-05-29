package com.kirandeep.ecommerce.authentication.dto;

import com.kirandeep.ecommerce.authentication.enums.Role;
import lombok.Data;

@Data
public class AppUserDto {
    private String email;
    private Role role;

}
