package com.sales_analytics.sales_analytics_service.controller;

import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsRequest;
import com.sales_analytics.sales_analytics_service.dto.OrderAnalyticsResponse;
import com.sales_analytics.sales_analytics_service.service.OrderAnalyticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-analytics")
@RequiredArgsConstructor
public class OrderAnalyticsController {

    private final OrderAnalyticsService orderAnalyticsService;

    @PostMapping
    public ResponseEntity<OrderAnalyticsResponse> submitOrder(@Valid @RequestBody OrderAnalyticsRequest request) {
        return ResponseEntity.ok(orderAnalyticsService.submitOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderAnalyticsResponse>> getOrderAnalytics() {
        return ResponseEntity.ok(orderAnalyticsService.getOrderAnalytics());
    }

    @GetMapping("/top-selling")
    public ResponseEntity<Page<OrderAnalyticsResponse>> getTopSellingProducts(
            @PageableDefault(size = 10, sort = "totalSoldCount", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(orderAnalyticsService.getTopSellingProducts(pageable));
    }

    @GetMapping("/top-revenue")
    public ResponseEntity<Page<OrderAnalyticsResponse>> getTopRevenueProducts(
            @PageableDefault(size = 10, sort = "totalRevenue", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(orderAnalyticsService.getTopRevenueProducts(pageable));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<OrderAnalyticsResponse>> getProductsByCategory(@PathVariable String category,
            @PageableDefault(size = 10, sort = "totalSoldCount", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(orderAnalyticsService.getProductsByCategory(category, pageable));
    }
}
