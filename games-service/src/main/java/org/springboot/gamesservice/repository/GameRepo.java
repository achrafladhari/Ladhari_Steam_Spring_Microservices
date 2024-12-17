package org.springboot.gamesservice.repository;

import org.springboot.gamesservice.games.GamesApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<GamesApp,Integer> {
    List<GamesApp> findAllByIdInOrderById(List<Integer> ids);
}
