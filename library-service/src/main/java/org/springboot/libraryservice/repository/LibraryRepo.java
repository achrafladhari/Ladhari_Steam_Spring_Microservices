package org.springboot.libraryservice.repository;

import org.springboot.libraryservice.library.LibraryApp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LibraryRepo extends MongoRepository<LibraryApp, String> {

    Optional<LibraryApp> findByUsername(String username);
    String deleteByUsername(String username);
}
