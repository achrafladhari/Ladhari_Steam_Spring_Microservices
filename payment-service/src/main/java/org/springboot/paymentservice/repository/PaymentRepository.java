package org.springboot.paymentservice.repository;

import org.springboot.paymentservice.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
