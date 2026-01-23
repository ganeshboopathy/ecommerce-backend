package com.sales_analytics.sales_analytics_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_analytics")
@Data
@Builder(toBuilder = true, builderClassName = "Builder")
@AllArgsConstructor
@NoArgsConstructor
public class ProductAnalytics {

    @Id
    @NotBlank(message = "Product ID must not be blank")

    // Foreign key
    private String productId;

    @DecimalMin(value = "0.0", message = "Average rating cannot be less than 0")
    @DecimalMax(value = "5.0", message = "Average rating cannot be more than 5")
    private double avgRating;

    @Min(value = 0, message = "Rating count cannot be negative")
    private long ratingCount;

    @DecimalMin(value = "0.0", message = "Rating cannot be less than 0")
    @DecimalMax(value = "5.0", message = "Rating cannot be more than 5")
    private double rating;

    @NotNull(message = "Total rating must not be null")
    @Min(value = 0, message = "Total rating cannot be negative")
    private double totalRating;
}
