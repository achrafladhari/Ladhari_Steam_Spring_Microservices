package org.springboot.orderservice.repository;

import org.springboot.orderservice.order.OrderApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderApp, Integer> {

    List<OrderApp> findByUsername(String username);

}
