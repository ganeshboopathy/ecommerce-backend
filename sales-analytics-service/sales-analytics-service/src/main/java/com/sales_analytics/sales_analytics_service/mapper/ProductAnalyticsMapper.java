package com.sales_analytics.sales_analytics_service.mapper;

import com.sales_analytics.sales_analytics_service.dto.RatingRequest;
import com.sales_analytics.sales_analytics_service.dto.RatingResponse;
import com.sales_analytics.sales_analytics_service.entity.ProductAnalytics;
import org.springframework.stereotype.Component;

@Component
public class ProductAnalyticsMapper {

    public ProductAnalytics toProductAnalytics(RatingRequest ratingRequest) {
        return ProductAnalytics.builder()
                .productId(ratingRequest.getProductId())
                .rating(ratingRequest.getRating())
                .build();
    }

    public RatingResponse toRatingResponse(ProductAnalytics productAnalytics) {
        return RatingResponse.builder()
                .productId(productAnalytics.getProductId())
                .avgRating(productAnalytics.getAvgRating())
                .ratingCount(productAnalytics.getRatingCount())
                .build();
    }
}
