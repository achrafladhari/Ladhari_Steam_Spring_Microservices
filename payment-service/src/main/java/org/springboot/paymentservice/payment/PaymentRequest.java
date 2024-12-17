package org.springboot.paymentservice.payment;

import org.springboot.paymentservice.user.UserApp;

public record PaymentRequest(
        Integer id,
        double amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        UserApp user
) {
}
