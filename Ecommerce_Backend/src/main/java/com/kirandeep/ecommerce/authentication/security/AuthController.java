package com.kirandeep.ecommerce.authentication.security;

import com.kirandeep.ecommerce.authentication.dto.AppUserDto;
import com.kirandeep.ecommerce.authentication.dto.AuthResponseDto;
import com.kirandeep.ecommerce.authentication.dto.LoginRequestDto;
import com.kirandeep.ecommerce.authentication.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AppUserDto> register(@RequestBody RegisterRequestDto registerRequestDto){

      AppUserDto appUserDto =  authService.register(registerRequestDto);
      return ResponseEntity.ok(appUserDto);

    }
@PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody  LoginRequestDto loginRequestDto){
      return ResponseEntity.ok(authService.login(loginRequestDto));

    }





}
