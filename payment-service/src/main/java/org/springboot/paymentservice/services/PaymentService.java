package org.springboot.paymentservice.services;
import lombok.RequiredArgsConstructor;
import org.springboot.paymentservice.mapper.PaymentMapper;
import org.springboot.paymentservice.payment.PaymentRequest;
import org.springboot.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public Integer createPayment(PaymentRequest request) {
        var payment = this.repository.save(this.mapper.toPayment(request));
        return payment.getId();
    }
}
