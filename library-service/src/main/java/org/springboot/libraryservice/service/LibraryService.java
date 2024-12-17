package org.springboot.libraryservice.service;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Library;
import org.springboot.libraryservice.games.Games;
import org.springboot.libraryservice.library.LibraryApp;
import org.springboot.libraryservice.purchase.PurchaseRequest;
import org.springboot.libraryservice.repository.LibraryRepo;
import org.springboot.libraryservice.user.UserApp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepo libraryRepo;

    public void createLibrary(UserApp user) {
        LibraryApp libraryToSave = new LibraryApp();
        libraryToSave.setGames(null);
        libraryToSave.setUsername(user.username());
        libraryRepo.save(libraryToSave);
    }

    public void updateLibrary(PurchaseRequest request) {
        LibraryApp libraryToUpdate = libraryRepo.findByUsername(request.username()).orElse(null);
        for (Games game:request.games()){
            if (libraryToUpdate.getGames()==null){
                libraryToUpdate.setGames(new ArrayList<>());
                libraryToUpdate.getGames().add(game);
            }
            else if (!libraryToUpdate.getGames().contains(game)){
                libraryToUpdate.getGames().add(game);
            }
        }
        libraryRepo.save(libraryToUpdate);
    }
}
