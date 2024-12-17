package org.springboot.orderservice.mapper;

import org.springboot.orderservice.order.OrderApp;
import org.springboot.orderservice.orderLine.OrderLine;
import org.springboot.orderservice.orderLine.OrderLineRequestWithoutId;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequestWithoutId request) {
        return OrderLine.builder()
                .gameId(request.gameId())
                .order(
                        OrderApp.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

}