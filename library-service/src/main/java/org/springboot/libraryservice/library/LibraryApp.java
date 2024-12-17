package org.springboot.libraryservice.library;


import lombok.*;
import org.springboot.libraryservice.games.Games;
import org.springboot.libraryservice.user.UserApp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class LibraryApp {
    @Id
    private String id;
    private String username;
    private List<Games> games;
}
