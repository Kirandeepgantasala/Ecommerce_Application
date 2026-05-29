package com.kirandeep.ecommerce.authentication.service;

import com.kirandeep.ecommerce.authentication.dto.AppUserDto;
import com.kirandeep.ecommerce.authentication.dto.AuthResponseDto;
import com.kirandeep.ecommerce.authentication.dto.LoginRequestDto;
import com.kirandeep.ecommerce.authentication.dto.RegisterRequestDto;
import com.kirandeep.ecommerce.authentication.entity.AppUser;
import com.kirandeep.ecommerce.authentication.enums.Role;
import com.kirandeep.ecommerce.authentication.repository.AppUserRepository;
import com.kirandeep.ecommerce.customer.entity.Customer;
import com.kirandeep.ecommerce.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements UserDetailsService {
private final AppUserRepository appUserRepository;
private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Email Not Found"+username));
    }
}
