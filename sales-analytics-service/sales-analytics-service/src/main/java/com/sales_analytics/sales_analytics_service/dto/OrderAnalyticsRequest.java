package com.sales_analytics.sales_analytics_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAnalyticsRequest {
    @NotBlank(message = "Product ID must not be blank")
    private String productId;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @Min(value = 0, message = "Quantity cannot be negative")
    private long quantity;

    @NotNull(message = "Revenue must not be null")
    @Min(value = 0, message = "Revenue cannot be negative")
    private BigDecimal revenue;
}
