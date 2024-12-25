package org.springboot.gateway;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.gateway.util.JwtUtil;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.Date;
import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class JwtGatewayTests {

    private JwtUtil jwtUtil;
    private String validToken;
    private String invalidToken;

    private static final String SECRET_KEY="413F4428472B4BB6250655368566D5970337336763979244226452948404D6351";

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();

        // Generate a valid token for testing
        validToken = Jwts.builder()
                .setSubject("test-user")
                .claim("roles", of("USER", "ADMIN"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour validity
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        // Create an invalid token (e.g., tampered or malformed)
        invalidToken = validToken.substring(0, validToken.length() - 1) + "x";
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Test
    void shouldValidateTokenSuccessfully() {
        // Act & Assert
        jwtUtil.validateToken(validToken); // No exceptions means the test passes
    }

    @Test
    void shouldThrowExceptionForInvalidToken() {
        // Act & Assert
        assertThatThrownBy(() -> jwtUtil.validateToken(invalidToken))
                .isInstanceOf(io.jsonwebtoken.JwtException.class)
                .hasMessageContaining("JWT");
    }

    @Test
    void shouldExtractRolesFromValidToken() {
        // Act
        List<String> roles = jwtUtil.getRolesFromToken(validToken);

        // Assert
        assertThat(roles).isNotNull();
        assertThat(roles).containsExactlyInAnyOrder("USER", "ADMIN");
    }


}
