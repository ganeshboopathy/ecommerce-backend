package com.product.product_service_api.dto.analytics;

import com.product.product_service_api.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAnalyticsResponse {
    // Product Details
    private String productId;
    private String name;
    private String price;
    private String description;
    private String category;

    // Analytics Details
    private Double avgRating;
    private Long ratingCount;
    private Long unitsSold;
    private BigDecimal totalRevenue;

    // Type of insight (e.g., "Top Selling", "Top Rated", "Top Revenue")
    private String insightType;

    public static ProductAnalyticsResponse from(Product product, RatingResponse rating) {
        return ProductAnalyticsResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .avgRating(rating.getAvgRating())
                .ratingCount(rating.getRatingCount())
                .insightType("Top Rated")
                .build();
    }

    public static ProductAnalyticsResponse from(Product product, OrderAnalyticsResponse analytics, String type,
            RatingResponse rating) {
        ProductAnalyticsResponse response = ProductAnalyticsResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .unitsSold(analytics.getTotalSoldCount())
                .totalRevenue(analytics.getTotalRevenue())
                .insightType(type)
                .build();

        if (rating != null) {
            response.setAvgRating(rating.getAvgRating());
            response.setRatingCount(rating.getRatingCount());
        }
        return response;
    }
}
