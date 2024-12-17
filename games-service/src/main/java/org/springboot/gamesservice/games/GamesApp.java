package org.springboot.gamesservice.games;


import jakarta.persistence.*;
import lombok.*;
import org.springboot.gamesservice.category.CategoryApp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "games")
public class GamesApp {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double avaiblity;
    private double price;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryApp category;

}
