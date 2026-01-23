package com.sales_analytics.sales_analytics_service.service;

import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsRequest;
import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderAnalyticsService {
    List<OrderAnalyticsResponse> getOrderAnalytics();

    List<OrderAnalyticsResponse> getHighSoldProducts();

    OrderAnalyticsResponse submitOrder(OrderAnalyticsRequest request);

    Page<OrderAnalyticsResponse> getTopSellingProducts(Pageable pageable);

    Page<OrderAnalyticsResponse> getTopRevenueProducts(Pageable pageable);

    Page<OrderAnalyticsResponse> getProductsByCategory(String category, Pageable pageable);
}
