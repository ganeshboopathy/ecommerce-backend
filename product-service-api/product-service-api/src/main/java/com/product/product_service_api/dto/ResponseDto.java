package com.product.product_service_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private String id;

    private String name;

    private String description;

    private Float star_rating;

    private Integer rating_count;

    private  Boolean  is_top_rated;

    private  String category;

    private LocalDateTime created_at;
    private  String price;
}
