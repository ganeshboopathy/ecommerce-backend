package com.order.order_service_api.mapper;

import com.order.order_service_api.dto.OrderRequest;
import com.order.order_service_api.dto.OrderResponse;
import com.order.order_service_api.entity.Order;
import com.order.order_service_api.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

    public Order mapToEntity(OrderRequest orderRequest) {
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(orderRequest.getSkuCode())
                .price(orderRequest.getPrice())
                .quantity(orderRequest.getQuantity())
                .orderStatus(OrderStatus.PLACED)
                .build();
    }

    public OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity(),
                order.getOrderStatus()
        );
    }
}
