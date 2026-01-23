package com.order.order_service_api.dto;

import com.order.order_service_api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private String skuCode;
    private Double price;
    private Integer quantity;
    private OrderStatus orderStatus;
}
