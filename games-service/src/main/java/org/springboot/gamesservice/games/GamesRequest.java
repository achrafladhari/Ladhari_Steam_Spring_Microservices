package org.springboot.gamesservice.games;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springboot.gamesservice.category.CategoryApp;

public record GamesRequest (
         Integer id,
       //  @NotNull(message = "Game name sould not be null!")
         String name,
      //   @NotNull(message = "Game description sould not be null!")
         String description,
      //   @Positive(message = "Game avaiblity sould be positive!")
         double avaiblity,
      //   @Positive(message = "Game price sould be positive!")
         double price,
     //    @NotNull (message = "Game category is required !")
         String image,
         Integer categoryId
){
}
