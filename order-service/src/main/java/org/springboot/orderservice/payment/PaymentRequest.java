package org.springboot.orderservice.payment;


import org.springboot.orderservice.order.PaymentMethod;
import org.springboot.orderservice.user.UserResponse;


public record PaymentRequest(
        double amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        UserResponse user
) {
}