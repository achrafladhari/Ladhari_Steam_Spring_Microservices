package org.springboot.orderservice.games;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

public record PurchaseRequest (
        @NotNull(message = "Game is mandatory")
        Integer gameId,
        @Positive(message = "Quantity is mandatory")
        double quantity
){
}
