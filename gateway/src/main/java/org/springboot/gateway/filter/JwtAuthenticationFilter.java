package org.springboot.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.gateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;
    private static final List<String> OPEN_ENDPOINTS = Arrays.asList(
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-ui/index.html",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/webjars/**", // Might be required if you use WebJars for Swagger UI resources
            "/swagger-resources",
            "/swagger-resources/**",
            "/eureka",
            "/api/v1/games",
            "/api/v1/games/{game-id}"
    );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.remove("X-Forwarded-For");
            httpHeaders.remove("X-Forwarded-Proto");
            // Remove other large headers if needed
        }).build();

        //List.of("/api/v1/auth/login", "/api/v1/auth/register", "/eureka", "/api/v1/games","/api/v1/games/{game-id}",WHITE_LIST_URL);

        Predicate<ServerHttpRequest> isApiSecured = r -> OPEN_ENDPOINTS.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (authMissing(request)) return onAccessDenied(exchange);

            String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            if (token != null && token.startsWith("Bearer ")) token = token.substring(7);

            try {

                jwtUtil.validateToken(token);
                List<String> roles = jwtUtil.getRolesFromToken(token);
                List<String> requiredRoles = exchange.getAttribute("requiredRoles");
                if (requiredRoles == null || !userHasRequiredRoles(roles, requiredRoles)) {
                    return onAccessDenied(exchange);
                }

            } catch (Exception e) {
                return onError(exchange);
            }
        }
        return chain.filter(exchange);
    }

    private boolean userHasRequiredRoles(List<String> userRoles, List<String> requiredRoles) {
        return userRoles.stream().anyMatch(requiredRoles::contains);
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> onAccessDenied(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
