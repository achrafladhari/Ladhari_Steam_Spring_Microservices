package org.springboot.userservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.userservice.request.UserRequest;
import org.springboot.userservice.services.UserService;
import org.springboot.userservice.user.ResponseMapper;
import org.springboot.userservice.user.UserApp;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //route for update user
    @PutMapping("/update/{user-id}")
    public ResponseEntity<String> updateUser (
           @PathVariable("user-id") String id, @RequestBody @Valid UserRequest request
    ){
       return ResponseEntity.ok(userService.updateUser(request, id));
    }

    //route for update user
    @PutMapping("/update/username/{username}")
    public ResponseEntity<ResponseMapper> updateUserByUsername (
            @PathVariable("username") String username, @RequestBody @Valid UserRequest request

    ){
        return ResponseEntity.ok(userService.updateUserByUsername(request, username));
    }

    //delete user
    @DeleteMapping("/{user-id}")
    public ResponseEntity<String> delete(
            @PathVariable("user-id") String userId,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(userService.deleteUser(userId,token));
    }


    //get user by id
    @GetMapping("/{user-id}")
    public ResponseEntity<UserApp> findById(
            @PathVariable("user-id") String userId

    ) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    //get user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserApp> findByUsername(
            @PathVariable("username") String username

    ) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

}
