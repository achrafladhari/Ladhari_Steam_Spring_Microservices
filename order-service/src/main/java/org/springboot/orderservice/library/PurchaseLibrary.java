package org.springboot.orderservice.library;

import org.springboot.orderservice.games.PurchaseResponse;

import java.util.List;

public record PurchaseLibrary(
        List<PurchaseResponse> games,
        String username
) {
}
