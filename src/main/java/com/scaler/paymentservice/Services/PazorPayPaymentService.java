package com.scaler.paymentservice.Services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PazorPayPaymentService implements PaymentService {
    private RazorpayClient razorpayClient;

    public PazorPayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(String orderID) throws RazorpayException {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",6900); //1000 means 10;
        // if you want to do transaction of 10.21 rupees we have to write 1021
        // for 9.50 rupee write 950
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        //paymentLinkRequest.put("first_min_partial_amount",100);

        paymentLinkRequest.put("expire_by",System.currentTimeMillis()+15*60*1000);
        // time in epoch timestamp
        // expire link at = current time + 15 mins
        paymentLinkRequest.put("reference_id",orderID);
        paymentLinkRequest.put("description","Payment for order no "+orderID);

        // customer details from order service using orderID;
        // Order order = orderService.getOrderDetails(orderID);
        // String customerName = order.getUser().getName();
        // String contact = order.getUser().getContact();

        //hard coding it for now
        JSONObject customer = new JSONObject();
        customer.put("name","+919116511733");
        customer.put("contact","Divyansh Rao");
        customer.put("email","divyanshrao15091509@gmail.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);

        JSONObject notes = new JSONObject();
        notes.put("OrderID","1. iPhone 15");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://sxyprn.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.get("short_url");
    }

    @Override
    public String getPaymentStatus(String paymentID) {
        // go to Db
        // check the status of payment in DB
        // if no - call the payment gateway to check status of old payment
        // update that status into its own DB
        // return the status
        return null;
    }
}
