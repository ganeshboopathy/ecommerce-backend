package com.product.product_service_api.controller;

import com.product.product_service_api.dto.RequestDto;
import com.product.product_service_api.dto.ResponseDto;
import com.product.product_service_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ResponseDto> getAll() {
        return productService.getall();
    }

    @GetMapping("/test")
    public String test() {
        return "work ";
    }

    @PostMapping
    public ResponseDto create(@Valid @RequestBody RequestDto dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("/top-selling")
    public List<com.product.product_service_api.dto.analytics.ProductAnalyticsResponse> getTopSellingProducts(
            @PageableDefault(size = 10) Pageable pageable) {
        return productService.getTopSellingProducts(pageable);
    }

    @GetMapping("/top-revenue")
    public List<com.product.product_service_api.dto.analytics.ProductAnalyticsResponse> getTopRevenueProducts(
            @PageableDefault(size = 10) Pageable pageable) {
        return productService.getTopRevenueProducts(pageable);
    }

    @GetMapping("/top-rated")
    public List<com.product.product_service_api.dto.analytics.ProductAnalyticsResponse> getTopRatedProducts(
            @PageableDefault(size = 10) Pageable pageable) {
        return productService.getTopRatedProducts(pageable);
    }

    @GetMapping("/{id}")
    public ResponseDto getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
}
