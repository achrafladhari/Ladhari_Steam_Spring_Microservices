package org.springboot.gamesservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.gamesservice.games.*;
import org.springboot.gamesservice.services.GamesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
public class GamesController {

    private final GamesService service;

    @PostMapping("/purchase")
    public ResponseEntity<List<GamePurchaseResponse>> purchaseGames(
            @RequestBody List<GamePurchaseRequest> request
    ) {
        return ResponseEntity.ok(service.purchaseGames(request));
    }

    @GetMapping("/{game-id}")
    public ResponseEntity<GamesApp> findById(
            @PathVariable("game-id") Integer gameId
    ) {
        return ResponseEntity.ok(service.findById(gameId));
    }

    @GetMapping
    public ResponseEntity<List<GamesApp>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
