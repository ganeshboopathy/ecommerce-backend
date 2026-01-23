package com.product.product_service_api.mapper;

import com.product.product_service_api.dto.RequestDto;
import com.product.product_service_api.dto.ResponseDto;
import com.product.product_service_api.entity.Product;
import org.springframework.stereotype.Component;

import static java.util.stream.DoubleStream.builder;

@Component
public class ProductMapper {
    public Product toEntity(RequestDto dto){
        return Product.builder()
                .price(dto.getPrice())
                .category(dto.getCategory())
                .name(dto.getName())
                .description(dto.getDescription())
                .rating_count(dto.getRating_count())
                .star_rating(dto.getStar_rating())
                .build();
    }
    public ResponseDto toResponseDto (Product product){
        return ResponseDto.builder()
                .id(product.getId())
                .category(product.getCategory())
                .price(product.getPrice())
                .created_at(product.getCreated_at())
                .rating_count(product.getRating_count())
                .description(product.getDescription())
                .is_top_rated(product.getIsTopRated())
                .star_rating(product.getStar_rating())
                .name(product.getName())
                .build();
    }
}
