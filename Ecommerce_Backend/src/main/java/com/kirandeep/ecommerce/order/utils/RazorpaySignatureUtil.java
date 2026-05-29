package com.kirandeep.ecommerce.order.utils;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;

import static org.springframework.security.crypto.codec.Hex.*;

public class RazorpaySignatureUtil {

    public static Boolean verifySignature(String payload,String expectedSignature,String secret){
        try {
            String actualSignature = calculateRFC2104HMAC(payload,secret);
            return actualSignature.equals(expectedSignature);
        } catch (SignatureException e) {
            throw new RuntimeException("Razorpay signature verification failed"+e.getMessage());
        }
    }



    private static String calculateRFC2104HMAC(String data,String secret) throws SignatureException {

        try{
            SecretKeySpec signingKey =
                    new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encode(rawHmac));
        }
        catch(Exception e){
            throw new SignatureException("Failed to generate HMAC: "+e.getMessage());

        }

    }
}
