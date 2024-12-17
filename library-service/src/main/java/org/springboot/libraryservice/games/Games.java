package org.springboot.libraryservice.games;

public record Games(
        Integer gameId,
        String name,
        String description,
        double price,
        double quantity
) {
}
