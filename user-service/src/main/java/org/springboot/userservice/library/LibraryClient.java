package org.springboot.userservice.library;

import org.springboot.userservice.user.UserApp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        name = "library-service",
        url = "${application.config.library-url}"
)
public interface LibraryClient {
    @PostMapping
    void createLibrary(@RequestBody UserApp requestBody, @RequestHeader("Authorization") String token);
}
