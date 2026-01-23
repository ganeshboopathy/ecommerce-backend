package com.sales_analytics.sales_analytics_service.service.implement;

import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsRequest;
import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsResponse;
import com.sales_analytics.sales_analytics_service.entity.OrderAnalytics;
import com.sales_analytics.sales_analytics_service.mapper.OrderAnalyticsMapper;
import com.sales_analytics.sales_analytics_service.repository.OrderAnalyticsRepository;
import com.sales_analytics.sales_analytics_service.service.OrderAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAnalyticsServiceImp implements OrderAnalyticsService {

    private final OrderAnalyticsRepository orderAnalyticsRepository;
    private final OrderAnalyticsMapper orderAnalyticsMapper;

    @Override
    public List<OrderAnalyticsResponse> getOrderAnalytics() {
        return orderAnalyticsRepository.findAll().stream()
                .map(orderAnalyticsMapper::toOrderAnalyticsResponse)
                .toList();
    }

    @Override
    public List<OrderAnalyticsResponse> getHighSoldProducts() {
        return orderAnalyticsRepository.findAllByOrderByTotalSoldCountDesc().stream()
                .map(orderAnalyticsMapper::toOrderAnalyticsResponse)
                .toList();
    }

    @Override
    public OrderAnalyticsResponse submitOrder(OrderAnalyticsRequest request) {
        OrderAnalytics orderAnalytics = orderAnalyticsRepository.findById(request.getProductId())
                .orElse(null);

        if (orderAnalytics == null) {
            orderAnalytics = orderAnalyticsMapper.toOrderAnalytics(request);
        } else {
            orderAnalytics.setTotalSoldCount(orderAnalytics.getTotalSoldCount() + request.getQuantity());
            orderAnalytics.setTotalRevenue(orderAnalytics.getTotalRevenue().add(request.getRevenue()));
        }

        OrderAnalytics savedEntity = orderAnalyticsRepository.save(orderAnalytics);
        return orderAnalyticsMapper.toOrderAnalyticsResponse(savedEntity);
    }

    @Override
    public Page<OrderAnalyticsResponse> getTopSellingProducts(Pageable pageable) {
        return orderAnalyticsRepository.findAllByOrderByTotalSoldCountDesc(pageable)
                .map(orderAnalyticsMapper::toOrderAnalyticsResponse);
    }

    @Override
    public Page<OrderAnalyticsResponse> getTopRevenueProducts(Pageable pageable) {
        return orderAnalyticsRepository.findAllByOrderByTotalRevenueDesc(pageable)
                .map(orderAnalyticsMapper::toOrderAnalyticsResponse);
    }

    @Override
    public Page<OrderAnalyticsResponse> getProductsByCategory(String category, Pageable pageable) {
        return orderAnalyticsRepository.findAllByCategoryOrderByTotalSoldCountDesc(category, pageable)
                .map(orderAnalyticsMapper::toOrderAnalyticsResponse);
    }
}