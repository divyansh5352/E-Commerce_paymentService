package com.scaler.paymentservice.Services;

import com.razorpay.RazorpayException;

public interface PaymentService {
    String createPaymentLink(String orderID) throws RazorpayException;
    String getPaymentStatus(String paymentID);
}
