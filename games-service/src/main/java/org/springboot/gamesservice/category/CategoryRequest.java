package org.springboot.gamesservice.category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.springboot.gamesservice.games.GamesApp;

import java.util.List;

public record CategoryRequest (
                Integer id,
                String name,
                String description
){
}
