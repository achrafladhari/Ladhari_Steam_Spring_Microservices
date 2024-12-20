package org.springboot.orderservice.library;


import org.springboot.orderservice.games.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "library-service",
        url = "${application.config.library-url}"
)
public interface LibraryClient {

    @PutMapping("/purchase")
    List<PurchaseResponse> purchaseLibrary(@RequestBody PurchaseLibrary requestBody, @RequestHeader("Authorization") String token);

    @GetMapping
    PurchaseLibrary getLibrary(@RequestParam String username, @RequestHeader("Authorization") String token);
}
