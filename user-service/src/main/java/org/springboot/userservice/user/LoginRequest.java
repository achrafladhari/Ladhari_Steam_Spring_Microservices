package org.springboot.userservice.user;

public record LoginRequest(
        String username,
        String password
) {
}
