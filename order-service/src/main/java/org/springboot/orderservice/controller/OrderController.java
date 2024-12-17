package org.springboot.orderservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.orderservice.order.OrderApp;
import org.springboot.orderservice.order.OrderRequest;
import org.springboot.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;


    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request,
            @RequestHeader("Authorization") String token
            ){
        return ResponseEntity.ok(service.createOrder(request,token));
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderApp>> getOrdersByUsername(
            @PathVariable("username") String username
    )
    {
        return ResponseEntity.ok(service.findByUsername(username));
    }
}
