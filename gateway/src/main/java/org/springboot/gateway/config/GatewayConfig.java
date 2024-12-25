package org.springboot.gateway.config;

import org.springboot.gateway.filter.JwtAuthenticationFilter;
import org.springboot.gateway.filter.RoleAssignmentFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class GatewayConfig {
    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }
    private final String ROLE_ADMIN="ADMIN";
    private final String ROLE_USER="USER";
    private final String USER_SERVICE="user-service";
    private final String URI_USER_SERVICE="lb://user-service";
    
    private final String PAYMENT_SERVICE="payment-service";
    private final String URI_PAYMENT_SERVICE="lb://payment-service";

    private final String ORDER_SERVICE="order-service";
    private final String URI_ORDER_SERVICE="lb://order-service";

    private final String GAMES_SERVICE="games-service";
    private final String URI_GAMES_SERVICE="lb://games-service";

    private final String LIBRARY_SERVICE="library-service";
    private final String URI_LIBRARY_SERVICE="lb://library-service";

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // USER SERVICE ROUTES
                .route(USER_SERVICE, r -> r.path("/api/v1/users/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN, ROLE_USER)))
                                .filter(filter))
                        .uri(URI_USER_SERVICE))

                .route(USER_SERVICE, r -> r.path("/api/v1/auth/**")
                        .uri(URI_USER_SERVICE))

                .route(USER_SERVICE, r -> r.path("/api/v1/user/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN)))
                                .filter(filter))
                        .uri(URI_USER_SERVICE))

                //SWAGGER
                .route(USER_SERVICE, r -> r.path("/users/swagger-ui/**")
                        .uri(URI_USER_SERVICE))
                .route(USER_SERVICE, r -> r.path("/users/v3/api-docs/**")
                        .uri(URI_USER_SERVICE)) // Forward to the user-service

                //GAMES SERVICE ROUTES
                .route(GAMES_SERVICE, r -> r.path("/api/v1/games/purchase")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN, ROLE_USER)))
                                .filter(filter))
                        .uri(URI_GAMES_SERVICE))

                .route(GAMES_SERVICE, r -> r.path("/api/v1/games")
                        .uri(URI_GAMES_SERVICE))

                .route(GAMES_SERVICE, r -> r.path("/api/v1/games/{gameId}/image")
                        .uri(URI_GAMES_SERVICE))

                .route(GAMES_SERVICE, r -> r.path("/api/v1/games/pagination")
                        .uri(URI_GAMES_SERVICE))

                .route(GAMES_SERVICE, r -> r.path("/api/v1/games/{games-id}")
                        .uri(URI_GAMES_SERVICE))

                .route(GAMES_SERVICE, r -> r.path("/api/v1/game/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN)))
                                .filter(filter))
                        .uri(URI_GAMES_SERVICE))

                .route(GAMES_SERVICE, r -> r.path("/api/v1/category/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN)))
                                .filter(filter))
                        .uri(URI_GAMES_SERVICE))

                //SWAGGER
                .route(GAMES_SERVICE, r -> r.path("/games/swagger-ui/**")
                        .uri(URI_GAMES_SERVICE))
                .route(GAMES_SERVICE, r -> r.path("/games/v3/api-docs/**")
                        .uri(URI_GAMES_SERVICE)) // Forward to the user-service

                //ORDER SERVICE ROUTES
                .route(ORDER_SERVICE, r -> r.path("/api/v1/orders/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN, ROLE_USER)))
                                .filter(filter))
                        .uri(URI_ORDER_SERVICE))


                .route(ORDER_SERVICE, r -> r.path("/api/v1/order/admin/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN)))
                                .filter(filter))
                        .uri(URI_ORDER_SERVICE))


                .route(ORDER_SERVICE, r -> r.path("/api/v1/order-lines/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_USER,ROLE_ADMIN)))
                                .filter(filter))
                        .uri(URI_ORDER_SERVICE))

                //SWAGGER
                .route(ORDER_SERVICE, r -> r.path("/order/swagger-ui/**")
                        .uri(URI_ORDER_SERVICE))
                .route(ORDER_SERVICE, r -> r.path("/order/v3/api-docs/**")
                        .uri(URI_ORDER_SERVICE)) // Forward to the user-service

                //PAYMENTS SERVICE ROUTES
                .route(PAYMENT_SERVICE, r -> r.path("/api/v1/payments/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN, ROLE_USER)))
                                .filter(filter))
                        .uri(URI_PAYMENT_SERVICE))

                //SWAGGER
                .route(PAYMENT_SERVICE, r -> r.path("/payment/swagger-ui/**")
                        .uri(URI_PAYMENT_SERVICE))
                .route(PAYMENT_SERVICE, r -> r.path("/payment/v3/api-docs/**")
                        .uri(URI_PAYMENT_SERVICE)) // Forward to the user-service


                //LIBRARY SERVICE ROUTES
                .route(LIBRARY_SERVICE, r -> r.path("/api/v1/library/**")
                        .filters(f -> f
                                .filter(new RoleAssignmentFilter(List.of(ROLE_ADMIN, ROLE_USER)))
                                .filter(filter))
                        .uri(URI_LIBRARY_SERVICE))

                //SWAGGER
                .route(LIBRARY_SERVICE, r -> r.path("/library/swagger-ui/**")
                        .uri(URI_LIBRARY_SERVICE))
                .route(LIBRARY_SERVICE, r -> r.path("/library/v3/api-docs/**")
                        .uri(URI_LIBRARY_SERVICE)) // Forward to the user-service


                .build();
    }




}
