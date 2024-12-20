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

    public LibraryApp getLibrary(String username){
        return libraryRepo.findByUsername(username).orElse(null);
    }
    public String deleteLibrary(String username){
        LibraryApp libraryToDelete = getLibrary(username);
        libraryRepo.delete(libraryToDelete);
        return libraryToDelete.getId();
    }
    public void updateLibrary(PurchaseRequest request) {
        LibraryApp libraryToUpdate = libraryRepo.findByUsername(request.username()).orElse(null);

        if (libraryToUpdate == null) {
            throw new IllegalArgumentException("Library not found for username: " + request.username());
        }

        if (libraryToUpdate.getGames() == null) {
            libraryToUpdate.setGames(new ArrayList<>());
        }

        List<Games> gamesToProcess = request.games() != null ? request.games() : new ArrayList<>();

        for (Games game : gamesToProcess) {
            if (!libraryToUpdate.getGames().contains(game)) {
                libraryToUpdate.getGames().add(game);
            }
        }

        libraryRepo.save(libraryToUpdate);
    }

}
