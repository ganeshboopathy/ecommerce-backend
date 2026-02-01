package com.order.order_service_api.service;

import com.order.order_service_api.dto.OrderRequest;
import com.order.order_service_api.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
}
