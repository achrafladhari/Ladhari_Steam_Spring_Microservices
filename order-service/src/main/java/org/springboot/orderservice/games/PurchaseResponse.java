package org.springboot.orderservice.games;


public record PurchaseResponse(
        Integer gameId,
        String name,
        String description,
        double price
) {
}