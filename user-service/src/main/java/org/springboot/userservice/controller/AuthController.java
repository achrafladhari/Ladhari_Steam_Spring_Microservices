package org.springboot.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.userservice.request.UserRequest;
import org.springboot.userservice.services.UserService;
import org.springboot.userservice.user.Address;
import org.springboot.userservice.user.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    //route for login
    @PostMapping("/login")
    public ResponseEntity<String> register(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(userService.login(request));
    }

    //route for creation user
    @PostMapping(value="/register")
    public ResponseEntity<String> createUser (
            @ModelAttribute @Valid UserRequest request
            , @RequestParam("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(userService.createUser(request,file));
    }


}
