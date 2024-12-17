package org.springboot.orderservice.mapper;

import org.springboot.orderservice.order.OrderApp;
import org.springboot.orderservice.order.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderApp toOrder(OrderRequest request,double amount) {
        if (request == null) {
            return null;
        }
        return OrderApp.builder()
                .id(request.id())
                .reference(request.reference())
                .totalAmount(amount)
                .paymentMethod(request.paymentMethod())
                .username(request.username())
                .build();
    }

}
