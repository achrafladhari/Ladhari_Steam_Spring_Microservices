package org.springboot.gamesservice.games;

public record GamePurchaseResponse(
        Integer gameId,
        String name,
        String description,
        double price,
        double quantity
) {
}
