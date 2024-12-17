package org.springboot.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class RoleAssignmentFilter implements GatewayFilter {

    private final List<String> requiredRoles;

    public RoleAssignmentFilter(List<String> requiredRoles) {
        this.requiredRoles = requiredRoles;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put("requiredRoles", requiredRoles);
        return chain.filter(exchange);
    }
}
