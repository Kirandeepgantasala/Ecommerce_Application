package com.kirandeep.ecommerce.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("${razorpay.key}")
    private String key;
    @Value("${razorpay.secret}")
    private String secret;


    @Bean
    public RazorpayClient razorpayClient(){
        {
            try {
                return new RazorpayClient(key,secret);
            } catch (RazorpayException e) {
                throw new RuntimeException("Failed to initialize Razorpay Client. Check your api keys",e);
            }
        }

    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }
}
