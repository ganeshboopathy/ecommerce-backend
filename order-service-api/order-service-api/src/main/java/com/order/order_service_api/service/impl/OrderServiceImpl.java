package com.order.order_service_api.service.impl;

import com.order.order_service_api.dto.OrderRequest;
import com.order.order_service_api.dto.OrderResponse;
import com.order.order_service_api.entity.Order;
import com.order.order_service_api.mapper.OrderMapper;
import com.order.order_service_api.repository.OrderRepository;
import com.order.order_service_api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.mapToEntity(orderRequest);
        orderRepository.save(order);
        // TODO: Publish OrderPlacedEvent to a message broker (e.g., Kafka)
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
