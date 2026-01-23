package com.sales_analytics.sales_analytics_service.service;

import com.sales_analytics.sales_analytics_service.dto.RatingRequest;
import com.sales_analytics.sales_analytics_service.dto.RatingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductAnalyticsService {

    RatingResponse giveRating(RatingRequest ratingRequest);

    Page<RatingResponse> getRatingAnalytics(Pageable pageable);

    RatingResponse getRatingById(String id);
}
