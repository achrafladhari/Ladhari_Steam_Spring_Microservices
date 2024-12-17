package org.springboot.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springboot.orderservice.order.OrderApp;
import org.springboot.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order/admin")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderService service;


    @GetMapping
    public ResponseEntity<List<OrderApp>> getAllOrders(
    )
    {
        return ResponseEntity.ok(service.findAllOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderApp> getOrderById(@PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(service.findById(orderId));
    }

}
