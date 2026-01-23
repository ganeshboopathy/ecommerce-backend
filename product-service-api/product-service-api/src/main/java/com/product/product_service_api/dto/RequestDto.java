package com.product.product_service_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {


    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Float star_rating;

    @NotNull
    private Integer rating_count;

    @NotBlank
    private  String category;
    @NotBlank
    private  String price;

}
