package org.springboot.orderservice.user;

public record UserResponse(
        String id,
        String name,
        String username,
        String email
) {

}