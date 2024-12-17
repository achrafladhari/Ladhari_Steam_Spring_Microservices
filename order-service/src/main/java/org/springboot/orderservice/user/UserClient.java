package org.springboot.orderservice.user;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url}"
)
public interface UserClient {
    @GetMapping("/username/{username}")
    Optional<UserResponse> findUserByUsername(@PathVariable("username") String username, @RequestHeader("Authorization") String token);
}
