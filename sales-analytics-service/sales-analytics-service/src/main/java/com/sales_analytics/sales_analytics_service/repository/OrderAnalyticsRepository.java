package com.sales_analytics.sales_analytics_service.repository;

import com.sales_analytics.sales_analytics_service.entity.OrderAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderAnalyticsRepository extends JpaRepository<OrderAnalytics, String> {
    List<OrderAnalytics> findAllByOrderByTotalSoldCountDesc();

    Page<OrderAnalytics> findAllByOrderByTotalSoldCountDesc(Pageable pageable);

    Page<OrderAnalytics> findAllByOrderByTotalRevenueDesc(Pageable pageable);

    Page<OrderAnalytics> findAllByCategoryOrderByTotalSoldCountDesc(String category, Pageable pageable);
}
