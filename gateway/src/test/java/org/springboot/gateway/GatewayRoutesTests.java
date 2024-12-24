package org.springboot.gateway;

import org.junit.jupiter.api.*;
import org.springboot.gateway.config.GatewayConfig;
import org.springboot.gateway.filter.JwtAuthenticationFilter;
import org.springboot.gateway.filter.RoleAssignmentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class GatewayRoutesTests {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private RouteLocatorBuilder routeLocatorBuilder;

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
