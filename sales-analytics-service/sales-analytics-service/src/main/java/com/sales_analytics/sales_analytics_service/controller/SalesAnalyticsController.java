package com.sales_analytics.sales_analytics_service.controller;

import com.sales_analytics.sales_analytics_service.dto.RatingRequest;
import com.sales_analytics.sales_analytics_service.dto.RatingResponse;
import com.sales_analytics.sales_analytics_service.service.ProductAnalyticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class SalesAnalyticsController {

    private final ProductAnalyticsService productAnalyticsService;

    @PostMapping("/rating")
    public ResponseEntity<RatingResponse> giveRating(@Valid @RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.ok(productAnalyticsService.giveRating(ratingRequest));
    }

    @GetMapping("/ratingAnalytics")
    public ResponseEntity<Page<RatingResponse>> getRatingAnalytics(Pageable pageable) {
        return ResponseEntity.ok(productAnalyticsService.getRatingAnalytics(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getRatingById(@PathVariable String id) {
        return ResponseEntity.ok(productAnalyticsService.getRatingById(id));
    }

}
