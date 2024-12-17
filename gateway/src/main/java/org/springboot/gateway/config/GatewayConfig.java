package org.springboot.gateway.config;

import org.springboot.gateway.filter.JwtAuthenticationFilter;
import org.springboot.gateway.filter.RoleAssignmentFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.util.Locale.filter;

@Configuration
public class GatewayConfig {
    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // USER SERVICE ROUTES
                .route("user-service", r -> r.path("/api/v1/users/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("USER", "ADMIN")))
                                .filter(filter))
                        .uri("lb://user-service"))

                .route("user-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))

                .route("user-service", r -> r.path("/api/v1/user/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("ADMIN")))
                                .filter(filter))
                        .uri("lb://user-service"))

                //SWAGGER
                .route("user-service", r -> r.path("/users/swagger-ui/**")
                        .uri("lb://user-service"))
                .route("user-service", r -> r.path("/users/v3/api-docs/**")
                        .uri("lb://user-service")) // Forward to the user-service


                //GAMES SERVICE ROUTES
                .route("games-service", r -> r.path("/api/v1/games/purchase")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("USER", "ADMIN")))
                                .filter(filter))
                        .uri("lb://games-service"))

                .route("games-service", r -> r.path("/api/v1/game/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("ADMIN")))
                                .filter(filter))
                        .uri("lb://games-service"))

                .route("games-service", r -> r.path("/api/v1/category/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("ADMIN")))
                                .filter(filter))
                        .uri("lb://games-service"))

                //SWAGGER
                .route("games-service", r -> r.path("/games/swagger-ui/**")
                        .uri("lb://games-service"))
                .route("games-service", r -> r.path("/games/v3/api-docs/**")
                        .uri("lb://games-service")) // Forward to the user-service

                //ORDER SERVICE ROUTES
                .route("order-service", r -> r.path("/api/v1/orders/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("USER", "ADMIN")))
                                .filter(filter))
                        .uri("lb://order-service"))


                .route("order-service", r -> r.path("/api/v1/order/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("ADMIN")))
                                .filter(filter))
                        .uri("lb://order-service"))


                .route("order-service", r -> r.path("/api/v1/order-lines/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("USER","ADMIN")))
                                .filter(filter))
                        .uri("lb://order-service"))

                //SWAGGER
                .route("order-service", r -> r.path("/order/swagger-ui/**")
                        .uri("lb://order-service"))
                .route("order-service", r -> r.path("/order/v3/api-docs/**")
                        .uri("lb://order-service")) // Forward to the user-service

                //PAYMENTS SERVICE ROUTES
                .route("payment-service", r -> r.path("/api/v1/payments/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("USER", "ADMIN")))
                                .filter(filter))
                        .uri("lb://payment-service"))

                //SWAGGER
                .route("payment-service", r -> r.path("/payment/swagger-ui/**")
                        .uri("lb://payment-service"))
                .route("payment-service", r -> r.path("/payment/v3/api-docs/**")
                        .uri("lb://payment-service")) // Forward to the user-service


                //LIBRARY SERVICE ROUTES
                .route("library-service", r -> r.path("/api/v1/library/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of("USER", "ADMIN")))
                                .filter(filter))
                        .uri("lb://library-service"))

                //SWAGGER
                .route("library-service", r -> r.path("/library/swagger-ui/**")
                        .uri("lb://library-service"))
                .route("library-service", r -> r.path("/library/v3/api-docs/**")
                        .uri("lb://library-service")) // Forward to the user-service


                .build();
    }




}
