package org.springboot.libraryservice.purchase;

import org.springboot.libraryservice.games.Games;

import java.util.List;

public record PurchaseRequest (
        List<Games> games,
        String username
){
}
