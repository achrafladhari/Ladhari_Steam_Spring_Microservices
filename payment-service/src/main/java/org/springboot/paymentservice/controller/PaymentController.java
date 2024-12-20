package org.springboot.paymentservice.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.paymentservice.payment.PaymentRequest;
import org.springboot.paymentservice.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentRequest request
    ) {
        return ResponseEntity.ok(this.service.createPayment(request));
    }

}
