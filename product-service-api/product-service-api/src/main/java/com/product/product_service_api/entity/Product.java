package com.product.product_service_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true,builderClassName = "Builder")
public class Product {
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private  String price;

    @NotBlank
    private String description;

    @NotNull
    private Float star_rating;

    @NotNull
    private Integer rating_count;

    @Column(name = "is_top_rated")
    @NotNull
    private  Boolean  isTopRated;

    @NotBlank
    private  String category;


    private LocalDateTime created_at;


}
