package org.springboot.userservice.repository;

import org.springboot.userservice.user.UserApp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<UserApp, String> {
    Optional<UserApp> findByUsername(String username);
}