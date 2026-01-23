package com.product.product_service_api.service;

import com.product.product_service_api.dto.RequestDto;
import com.product.product_service_api.dto.ResponseDto;
import com.product.product_service_api.dto.analytics.ProductAnalyticsResponse;

import java.util.List;

public interface ProductService {
    List<ResponseDto> getall();

    ResponseDto createProduct(RequestDto dto);

    List<ResponseDto> getTopProduct();

    List<ProductAnalyticsResponse> getTopSellingProducts(org.springframework.data.domain.Pageable pageable);

    List<ProductAnalyticsResponse> getTopRevenueProducts(org.springframework.data.domain.Pageable pageable);

    List<ProductAnalyticsResponse> getTopRatedProducts(org.springframework.data.domain.Pageable pageable);

    ResponseDto getProductById(String id);
}
