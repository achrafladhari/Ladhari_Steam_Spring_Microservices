package org.springboot.gamesservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.gamesservice.games.GamesRequest;
import org.springboot.gamesservice.services.GamesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game/admin")
public class AdminGameController {

    private final GamesService service;

    //CRUD GAMES
    @PostMapping
    public ResponseEntity<Integer> createGame(
            @ModelAttribute @Valid GamesRequest request
            , @RequestParam("file") MultipartFile file
    )  throws IOException {
        return ResponseEntity.ok(service.createGame(request,file));
    }

    @PutMapping("/{game-id}")
    public ResponseEntity<Integer> updateGame(
           @PathVariable("game-id") Integer id, @RequestBody @Valid GamesRequest request
    ){
        return ResponseEntity.ok(service.updateGame(request,id));
    }

    @DeleteMapping("/{game-id}")
    public ResponseEntity<Integer> deleteGame(
            @PathVariable("game-id") Integer id
    ){
        return ResponseEntity.ok(service.deleteGame(id));
    }

}
