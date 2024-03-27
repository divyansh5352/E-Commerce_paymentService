package com.scaler.paymentservice.Configurations;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {
    @Value("${razorpay.key_id}")
    private String razorpayKey;
    @Value("${razorpay.key_secret}")
    private String razorpaySecret;
    @Bean
    public RazorpayClient createRazorPayClient() throws RazorpayException {
        return new RazorpayClient(razorpayKey,razorpaySecret);

    }
}
