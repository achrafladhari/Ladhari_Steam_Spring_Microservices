package org.springboot.gamesservice.repository;

import org.springboot.gamesservice.games.GamesApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepo extends JpaRepository<GamesApp,Integer> {
    List<GamesApp> findAllByIdInOrderById(List<Integer> ids);

    @Query("SELECT g " +
            "FROM GamesApp g " +
            "WHERE (:name IS NULL OR :name = '' OR g.name LIKE %:name%) " +
            "ORDER BY g.id DESC")
    Page<GamesApp> findByNameContaining(@Param("name") String name,
                                    Pageable pageable);
}
