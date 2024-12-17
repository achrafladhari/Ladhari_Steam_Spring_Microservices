package org.springboot.gamesservice.services;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springboot.gamesservice.category.CategoryApp;
import org.springboot.gamesservice.exception.GamesPurchaseException;
import org.springboot.gamesservice.games.*;
import org.springboot.gamesservice.mapper.GameMapper;
import org.springboot.gamesservice.repository.GameRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GamesService {
    private final GameRepo repository;
    private final GameMapper mapper;

    //IMAGEEEE
    private String giveMeNewName(String oldName)
    {

        String firstpart = oldName.substring(0, oldName.lastIndexOf("."));
        String secondpart = oldName.substring(oldName.lastIndexOf(".") + 1);
        return firstpart + System.currentTimeMillis() + "." + secondpart;
    }

    @Value("${uploads.dir}")
    private String uploadDir;
    public String saveImage2(MultipartFile mf) throws IOException {
        String newName = giveMeNewName(mf.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectory(uploadPath);
        Path pathFile = uploadPath.resolve(newName);
        Files.write(pathFile, mf.getBytes());
        return newName;
    }

    //creates a game and convert from game request to gameapp then save it to BDD
    public Integer createGame(
            GamesRequest request,
            MultipartFile mf
    ) throws IOException {
        var game = mapper.toGame(request);
        if (!mf.isEmpty()){
            game.setImage(saveImage2(mf));
        }
        return repository.save(game).getId();
    }

    //update Game
    public Integer updateGame(
            GamesRequest request,
            Integer gameId
    ){
        var gameToUpdate = repository.findById(gameId).orElseThrow(EntityNotFoundException::new);
        mergeGame(gameToUpdate,request);
        repository.save(gameToUpdate);
        return gameToUpdate.getId();
    }

    private void mergeGame(GamesApp game, GamesRequest gamesRequest) {
        CategoryApp category = new CategoryApp();
        category.setId(gamesRequest.categoryId());
        if (StringUtils.isNotBlank(gamesRequest.name())){
            game.setName(gamesRequest.name());
        }
        if (StringUtils.isNotBlank(gamesRequest.description())){
            game.setDescription(gamesRequest.description());
        }
        if (gamesRequest.price()!=0.0){
            game.setPrice(gamesRequest.price());
        }
        if (gamesRequest.avaiblity()!=0.0){
            game.setAvaiblity(gamesRequest.avaiblity());
        }
        if (gamesRequest.categoryId()!=null){
            game.setCategory(category);
        }
    }

    //delete Game
    public Integer deleteGame(Integer gameId) {
        repository.deleteById(gameId);
        return gameId;
    }

    //return a game by id and convert from gameapp to gameResponse if there is no game with that it, it throw excp
    public GamesApp findById(Integer id) {
        return repository.findById(id)

                .orElseThrow(() -> new EntityNotFoundException("Game not found with ID:: " + id));
    }

    //return all games and convert from gameApp to game response
    public List<GamesApp> findAll() {
        return repository.findAll();
    }


    @Transactional(rollbackFor = GamesPurchaseException.class)
    public List<GamePurchaseResponse> purchaseGames(
            List<GamePurchaseRequest> request
    ) {
        //get gamesIds that will be purchased (gameIds is a list)
        var gameIds = request
                .stream()
                .map(GamePurchaseRequest::gameId)
                .toList();
        //get Games from BDD with redifined method in gamerepo
        var storedGames = repository.findAllByIdInOrderById(gameIds);
        // if gameIds != storedGames (fetched from BDD) that means there is a game or more that not exist in BDD
        if (gameIds.size() != storedGames.size()) {
            throw new GamesPurchaseException("One or more games does not exist");
        }

        //sorting gameids from request
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(GamePurchaseRequest::gameId))
                .toList();

        var purchasedGames = new ArrayList<GamePurchaseResponse>();
        for (int i = 0; i < storedGames.size(); i++) {
            var game = storedGames.get(i);
            var gameRequest = sortedRequest.get(i);
            if (game.getAvaiblity() < gameRequest.quantity()) {
                throw new GamesPurchaseException("Insufficient stock quantity for game with ID:: " + gameRequest.gameId());
            }
            var newAvailableQuantity = game.getAvaiblity() - gameRequest.quantity();
            game.setAvaiblity(newAvailableQuantity);
            repository.save(game);
            purchasedGames.add(mapper.toGamePurchaseResponse(game, gameRequest.quantity()));
        }
        return purchasedGames;
    }


}
