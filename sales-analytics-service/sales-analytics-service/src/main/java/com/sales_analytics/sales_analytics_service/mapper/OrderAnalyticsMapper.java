package com.sales_analytics.sales_analytics_service.mapper;

import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsRequest;
import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsResponse;
import com.sales_analytics.sales_analytics_service.entity.OrderAnalytics;
import org.springframework.stereotype.Component;

@Component
public class OrderAnalyticsMapper {

    public OrderAnalyticsResponse toOrderAnalyticsResponse(OrderAnalytics orderAnalytics) {
        return OrderAnalyticsResponse.builder()
                .productId(orderAnalytics.getProductId())
                .category(orderAnalytics.getCategory())
                .totalSoldCount(orderAnalytics.getTotalSoldCount())
                .totalRevenue(orderAnalytics.getTotalRevenue())
                .build();
    }

    public OrderAnalytics toOrderAnalytics(OrderAnalyticsRequest request) {
        return new OrderAnalytics(request.getProductId(), request.getCategory(), request.getQuantity(),
                request.getRevenue());
    }
}
