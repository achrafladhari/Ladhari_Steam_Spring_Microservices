package org.springboot.orderservice.orderLine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequestWithoutId(Integer orderId,  Integer gameId,
                                         double quantity) {
}
