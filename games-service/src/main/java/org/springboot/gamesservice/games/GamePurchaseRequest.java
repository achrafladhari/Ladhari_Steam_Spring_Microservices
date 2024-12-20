package org.springboot.gamesservice.games;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GamePurchaseRequest(
        @NotNull(message = "Game is mandatory")
        Integer gameId
) {
}
