package org.springboot.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springboot.userservice.services.UserService;
import org.springboot.userservice.user.UserApp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/admin")
public class UserAdminController {
    private final UserService userService;

    // check if user exists
    @GetMapping("/exists/{user-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("user-id") String userId
    ) {
        return ResponseEntity.ok(userService.existsById(userId));
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<UserApp>> findAll(){
        return ResponseEntity.ok(userService.findAllUsers());
    }


}
