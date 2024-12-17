package org.springboot.orderservice.games;
import lombok.RequiredArgsConstructor;
import org.springboot.orderservice.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
        name = "games-service",
        url = "${application.config.games-url}"
)
public interface GameClient {
    @PostMapping("/purchase")
    List<PurchaseResponse> purchaseGames(@RequestBody List<PurchaseRequest> requestBody, @RequestHeader("Authorization") String token);
}