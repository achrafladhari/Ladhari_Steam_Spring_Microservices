package org.springboot.paymentservice.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserApp(
        String id,
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "Username is required")
        String username,
        @NotNull(message = "Email is required")
        @Email(message = "The user email is not correctly formatted")
        String email
) {
}
