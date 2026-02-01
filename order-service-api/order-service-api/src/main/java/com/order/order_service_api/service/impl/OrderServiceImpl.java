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
    private final com.order.order_service_api.client.ProductClient productClient;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        // 1. Fetch Product Details
        com.order.order_service_api.dto.ProductDTO productDTO = productClient.getProductById(orderRequest.getId());

        // 2. Business Logic Calculations
        Double unitPrice = Double.parseDouble(productDTO.getPrice());
        Double totalPrice = unitPrice * orderRequest.getQuantity();
        String dynamicSkuCode = productDTO.getCategory() + "-[" + productDTO.getName() + "]";

        // 3. Build Entity (Manual mapping preferred due to logic)
        Order order = Order.builder()
                .orderNumber(java.util.UUID.randomUUID().toString())
                .productId(orderRequest.getId())
                .skuCode(dynamicSkuCode)
                .price(unitPrice)
                .totalPrice(totalPrice)
                .quantity(orderRequest.getQuantity())
                .orderStatus(com.order.order_service_api.enums.OrderStatus.PLACED)
                .build();

        // 4. Save and Return
        orderRepository.save(order);
        return orderMapper.mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
