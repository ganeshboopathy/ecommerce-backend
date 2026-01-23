package com.sales_analytics.sales_analytics_service.mapper;

import com.sales_analytics.sales_analytics_service.dto.RatingRequest;
import com.sales_analytics.sales_analytics_service.dto.RatingResponse;
import com.sales_analytics.sales_analytics_service.entity.ProductAnalytics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductAnalyticsMapperTest {

    private ProductAnalyticsMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProductAnalyticsMapper();
    }

    @Test
    void toRatingResponse_WithRatingRequest_ShouldMapToProductAnalytics() {
        // Arrange
        RatingRequest request = RatingRequest.builder()
                .productId("PROD-123")
                .rating(4.5)
                .build();

        // Act
        ProductAnalytics entity = mapper.toProductAnalytics(request);

        // Assert
        assertThat(entity).isNotNull();
        assertThat(entity.getProductId()).isEqualTo("PROD-123");
        assertThat(entity.getRating()).isEqualTo(4.5);
    }

    @Test
    void toRatingResponse_WithProductAnalytics_ShouldMapToRatingResponse() {
        // Arrange
        ProductAnalytics entity = ProductAnalytics.builder()
                .productId("PROD-456")
                .avgRating(4.2)
                .ratingCount(100L)
                .build();

        // Act
        RatingResponse response = mapper.toRatingResponse(entity);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo("PROD-456");
        assertThat(response.getAvgRating()).isEqualTo(4.2);
        assertThat(response.getRatingCount()).isEqualTo(100L);
    }
}
