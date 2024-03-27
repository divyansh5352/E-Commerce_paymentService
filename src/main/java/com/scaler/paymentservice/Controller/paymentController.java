package com.scaler.paymentservice.Controller;

import com.razorpay.RazorpayException;
import com.scaler.paymentservice.DTOs.CreatePaymentLinkRequestDTO;
import com.scaler.paymentservice.Services.PaymentService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class paymentController {
    private PaymentService paymentService;

    public paymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDTO requestDTO) throws RazorpayException {
        String link = paymentService.createPaymentLink(requestDTO.getOrderID());
        return link;
    }
    @PostMapping("/webhook")
    public void handelWebHookEvent(@RequestBody JSONObject WebhookEvent ){
        System.out.println("Webhook called " + WebhookEvent);
    }
}

