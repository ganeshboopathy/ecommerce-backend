package com.product.product_service_api.client;

import com.product.product_service_api.dto.analytics.OrderAnalyticsResponse;
import com.product.product_service_api.dto.analytics.RatingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sales-analytics-service")
public interface SalesAnalyticsClient {

    @GetMapping("/order-analytics/top-selling")
    Page<OrderAnalyticsResponse> getTopSellingProducts(Pageable pageable);

    @GetMapping("/order-analytics/top-revenue")
    Page<OrderAnalyticsResponse> getTopRevenueProducts(Pageable pageable);

    @GetMapping("/analytics/ratingAnalytics")
    Page<RatingResponse> getRatingAnalytics(Pageable pageable);

    @GetMapping("/analytics/{id}")
    RatingResponse getRatingById(@PathVariable("id") String id);
}
