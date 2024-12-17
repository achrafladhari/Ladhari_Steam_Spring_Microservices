package org.springboot.gamesservice.category;

import jakarta.persistence.*;
import lombok.*;
import org.springboot.gamesservice.games.GamesApp;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "category")
public class CategoryApp {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<GamesApp> games;
}
