package com.product.product_service_api.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAnalyticsResponse {
    private String productId;
    private String category;
    private long totalSoldCount;
    private BigDecimal totalRevenue;
}
