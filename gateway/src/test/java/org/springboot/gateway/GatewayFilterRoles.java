package org.springboot.gateway;

import org.junit.Test;
import org.springboot.gateway.filter.RoleAssignmentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GatewayFilterRoles {

    @Autowired
    private RoleAssignmentFilter roleAssignmentFilter;
    @Test
    public void testFilter() {
        // Mock ServerWebExchange and GatewayFilterChain
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        // Mock the exchange attributes map
        Map<String, Object> attributes = new HashMap<>();
        when(exchange.getAttributes()).thenReturn(attributes);

        // Mock chain behavior
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        // Test filter
        List<String> requiredRoles = List.of("ROLE_USER", "ROLE_ADMIN");
        RoleAssignmentFilter filter = new RoleAssignmentFilter(requiredRoles);

        Mono<Void> result = filter.filter(exchange, chain);

        // Verify behavior
        assertEquals(requiredRoles, attributes.get("requiredRoles")); // Check the attribute
        verify(chain, times(1)).filter(exchange); // Ensure chain.filter is called
        assertEquals(Mono.empty(), result); // Ensure the returned Mono is as expected
    }
}
