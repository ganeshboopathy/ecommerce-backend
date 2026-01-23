package com.sales_analytics.sales_analytics_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderAnalytics {
    @Id
    @NotBlank(message = "Product ID must not be blank")

    // Foreign key
    private String productId;

    // Snapshot (copied once, not live)
    @NotBlank(message = "Category must not be blank")
    private String category;

    // Selling metrics
    @Min(value = 0, message = "Total sold count cannot be negative")
    private long totalSoldCount;

    @NotNull(message = "Total revenue must not be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total revenue cannot be negative")
    private BigDecimal totalRevenue;
}
