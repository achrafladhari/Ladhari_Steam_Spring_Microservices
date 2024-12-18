package org.springboot.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springboot.userservice.services.UserService;
import org.springboot.userservice.user.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //get Users with pagination

    @GetMapping("/pagination")
    public ResponseEntity<Page<UserApp>> findAllByNameContaining(
          @RequestParam(required = false) String name,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable= PageRequest.of(page,size);
        return ResponseEntity.ok(userService.getUsersPagination(name,pageable));
    }


}
