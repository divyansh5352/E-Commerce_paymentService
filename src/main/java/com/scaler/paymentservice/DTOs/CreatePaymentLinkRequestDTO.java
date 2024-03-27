package com.scaler.paymentservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkRequestDTO {
    private String orderID;
}
