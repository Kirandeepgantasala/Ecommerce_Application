package com.kirandeep.ecommerce.authentication.security;

import com.kirandeep.ecommerce.authentication.entity.AppUser;
import com.kirandeep.ecommerce.authentication.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AppUserRepository appUserRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final HandlerMapping resourceHandlerMapping;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if(path.startsWith("/auth/") ||
                path.startsWith("/categories") ||
                path.startsWith("/products")){
            filterChain.doFilter(request,response);
            return;
        }

        try {
            System.out.println("JWT Filter Hit");

            final String authHeader = request.getHeader("Authorization");
            System.out.println("Incoming request" + path);
            System.out.println(request.getHeader("Authorization"));

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }


                String token = authHeader.substring(7);
            System.out.println("Token Extracted"+token);
                String email = jwtUtil.getEmailFromToken(token);
            System.out.println("Email Extracted"+email);
            System.out.println("AuthHeader"+authHeader);
                System.out.println("Token"+token+" "+"Email"+email);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    AppUser user = appUserRepository.findByEmail(email).orElseThrow();

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    System.out.println(user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            System.out.println("Before filterChain");
            filterChain.doFilter(request,response);
            System.out.println("After filterChain");
        }

        catch(Exception ex){
this.handlerExceptionResolver.resolveException(request,response,null,ex);
ex.printStackTrace();
        }

    }
}
