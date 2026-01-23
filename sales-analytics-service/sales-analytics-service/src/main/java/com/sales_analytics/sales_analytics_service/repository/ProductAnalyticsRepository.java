package com.sales_analytics.sales_analytics_service.repository;

import com.sales_analytics.sales_analytics_service.entity.ProductAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductAnalyticsRepository extends JpaRepository<ProductAnalytics, String> {
    @Query("SELECT p FROM ProductAnalytics p ORDER BY (p.avgRating * p.ratingCount + 3.5 * 5) / (p.ratingCount + 5) DESC")
    Page<ProductAnalytics> findTopRatedProducts(Pageable pageable);
}
