package com.sales_analytics.sales_analytics_service.service.implement;

import com.sales_analytics.sales_analytics_service.dto.RatingRequest;
import com.sales_analytics.sales_analytics_service.dto.RatingResponse;
import com.sales_analytics.sales_analytics_service.entity.ProductAnalytics;
import com.sales_analytics.sales_analytics_service.mapper.ProductAnalyticsMapper;
import com.sales_analytics.sales_analytics_service.repository.ProductAnalyticsRepository;
import com.sales_analytics.sales_analytics_service.service.ProductAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAnalyticsServiceImp implements ProductAnalyticsService {

    private final ProductAnalyticsRepository productAnalyticsRepository;
    private final ProductAnalyticsMapper productAnalyticsMapper;

    @Override
    public RatingResponse giveRating(RatingRequest ratingRequest) {
        ProductAnalytics productAnalytics = productAnalyticsRepository.findById(ratingRequest.getProductId())
                .orElse(null);
        if (productAnalytics == null) {
            productAnalytics = productAnalyticsMapper.toProductAnalytics(ratingRequest);
        }
        productAnalytics.setRatingCount(productAnalytics.getRatingCount() + 1);
        productAnalytics.setTotalRating(productAnalytics.getTotalRating() + ratingRequest.getRating());
        double avg = productAnalytics.getTotalRating() / productAnalytics.getRatingCount();
        productAnalytics.setAvgRating(Math.round(avg * 10.0) / 10.0);
        ProductAnalytics savedEntity = productAnalyticsRepository.save(productAnalytics);
        return productAnalyticsMapper.toRatingResponse(savedEntity);
    }

    @Override
    public Page<RatingResponse> getRatingAnalytics(Pageable pageable) {
        return productAnalyticsRepository.findTopRatedProducts(pageable)
                .map(productAnalyticsMapper::toRatingResponse);
    }

    @Override
    public RatingResponse getRatingById(String id) {
        return productAnalyticsRepository.findById(id).map(productAnalyticsMapper::toRatingResponse)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
    }
}
