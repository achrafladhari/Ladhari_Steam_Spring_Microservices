package org.springboot.gateway;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springboot.gateway.config.GatewayConfig;
import org.springboot.gateway.filter.JwtAuthenticationFilter;
import org.springboot.gateway.filter.RoleAssignmentFilter;
import org.springboot.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.Key;
import java.util.Date;
import java.util.List;

import static java.util.List.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springboot.gateway.util.JwtUtil.SECRET_KEY;

@SpringBootTest
class GatewayApplicationTests {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private RouteLocatorBuilder routeLocatorBuilder;

	private JwtUtil jwtUtil;
	private String validToken;
	private String invalidToken;

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

	@Test
	void shouldDefineRoutesCorrectly() {
		// Arrange
		GatewayConfig gatewayConfig = new GatewayConfig(jwtAuthenticationFilter);

		// Act
		RouteLocator routeLocator = gatewayConfig.routes(routeLocatorBuilder);

		// Assert
		assertThat(routeLocator).isNotNull();

		var routes = routeLocator.getRoutes().collectList().block();
		assertThat(routes).isNotNull();
		assertThat(routes).hasSizeGreaterThan(0);

		assertThat(routes)
				.anyMatch(route -> route.getId().equals("user-service") &&
						route.getUri().toString().equals("lb://user-service") &&
						route.getFilters().stream()
								.anyMatch(filter -> filter.toString().contains(RoleAssignmentFilter.class.getSimpleName())));

		assertThat(routes)
				.anyMatch(route -> route.getId().equals("games-service") &&
						route.getUri().toString().equals("lb://games-service") &&
						route.getFilters().stream()
								.anyMatch(filter -> filter.toString().contains(RoleAssignmentFilter.class.getSimpleName())));

		assertThat(routes)
				.anyMatch(route -> route.getId().equals("order-service") &&
						route.getUri().toString().equals("lb://order-service") &&
						route.getFilters().stream()
								.anyMatch(filter -> filter.toString().contains(RoleAssignmentFilter.class.getSimpleName())));
		assertThat(routes)
				.anyMatch(route -> route.getId().equals("payment-service") &&
						route.getUri().toString().equals("lb://payment-service") &&
						route.getFilters().stream()
								.anyMatch(filter -> filter.toString().contains(RoleAssignmentFilter.class.getSimpleName())));

		assertThat(routes)
				.anyMatch(route -> route.getId().equals("library-service") &&
						route.getUri().toString().equals("lb://library-service") &&
						route.getFilters().stream()
								.anyMatch(filter -> filter.toString().contains(RoleAssignmentFilter.class.getSimpleName())));
	}

}
