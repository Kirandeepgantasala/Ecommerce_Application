package com.kirandeep.ecommerce.authentication.security;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

private final AppUserRepository appUserRepository;
private final CustomerRepository customerRepository;
private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;
private final JwtUtil jwtUtil;


    public AppUserDto register(RegisterRequestDto registerRequestDto) {

      Optional<AppUser> appUser  = appUserRepository.findByEmail(registerRequestDto.getEmail());

      if(appUser.isPresent()){
          throw new RuntimeException("Email Already Exists");
      }
        AppUser newUser = new AppUser();
        newUser.setEmail(registerRequestDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        newUser.setRole(Role.CUSTOMER);
        newUser.setEnabled(true);

        Customer customer = new Customer();
        customer.setEmail(registerRequestDto.getEmail());
        customer.setName(registerRequestDto.getName());
        customer.setPhoneNumber(registerRequestDto.getPhoneNumber());
        customer.setAppUser(newUser);
        Customer savedCustomer = customerRepository.save(customer);

        AppUserDto dto = new AppUserDto();
        dto.setEmail(savedCustomer.getEmail());
        dto.setRole(savedCustomer.getAppUser().getRole());

        return dto;
    }


    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
       Authentication authentication =
                authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));

                       AppUser user = (AppUser) authentication.getPrincipal();


                       String token = jwtUtil.generateToken(user);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setEmail(user.getEmail());
        authResponseDto.setToken(token);
        authResponseDto.setRole(user.getRole());

        return authResponseDto;


    }



}
