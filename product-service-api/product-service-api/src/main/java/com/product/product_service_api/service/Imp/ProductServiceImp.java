package com.product.product_service_api.service.Imp;

import com.product.product_service_api.dto.RequestDto;
import com.product.product_service_api.dto.ResponseDto;
import com.product.product_service_api.entity.Product;
import com.product.product_service_api.mapper.ProductMapper;
import com.product.product_service_api.repository.ProductRepository;
import com.product.product_service_api.service.ProductService;
import com.product.product_service_api.util.IdGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.product.product_service_api.client.SalesAnalyticsClient;
import com.product.product_service_api.dto.analytics.OrderAnalyticsResponse;
import com.product.product_service_api.dto.analytics.ProductAnalyticsResponse;
import com.product.product_service_api.dto.analytics.RatingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final SalesAnalyticsClient salesAnalyticsClient;

    private final ProductMapper productMapper;

    @Override
    public List<ResponseDto> getall() {
        List<Product> products = productRepository.findAll();
        List<ResponseDto> responseDtos = new ArrayList<>(
                products.stream().map(productMapper::toResponseDto).toList());

        Map<String, RatingResponse> ratingMap = fetchRatingMap(0, 100);
        responseDtos.forEach(dto -> {
            if (ratingMap.containsKey(dto.getId())) {
                RatingResponse rating = ratingMap.get(dto.getId());
                dto.setStar_rating((float) rating.getAvgRating());
                dto.setRating_count((int) rating.getRatingCount());
            }
        });
        return responseDtos;
    }

    @Override
    public ResponseDto getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        ResponseDto responseDto = productMapper.toResponseDto(product);

        try {
            RatingResponse rating = salesAnalyticsClient.getRatingById(id);
            if (rating != null) {
                responseDto.setStar_rating((float) rating.getAvgRating());
                responseDto.setRating_count((int) rating.getRatingCount());
            }
        } catch (Exception e) {
            // Fallback to local database ratings
        }
        return responseDto;
    }

    @Override
    public ResponseDto createProduct(RequestDto dto) {
        Product product = productMapper.toEntity(dto);
        product.setCreated_at(LocalDateTime.now());
        product.setIsTopRated(dto.getStar_rating() > 4.5);
        product.setId(IdGeneratorUtil.generateProductId());
        return productMapper.toResponseDto(productRepository.save(product));

    }

    @Override
    public List<ResponseDto> getTopProduct() {
        return productRepository.findAllByIsTopRatedTrue().stream().map(productMapper::toResponseDto).toList();
    }

    @Override
    public List<ProductAnalyticsResponse> getTopSellingProducts(Pageable pageable) {
        Page<OrderAnalyticsResponse> analytics = salesAnalyticsClient.getTopSellingProducts(pageable);
        return enrichProducts(analytics.getContent(), "Top Selling");
    }

    @Override
    public List<ProductAnalyticsResponse> getTopRevenueProducts(Pageable pageable) {
        Page<OrderAnalyticsResponse> analytics = salesAnalyticsClient.getTopRevenueProducts(pageable);
        return enrichProducts(analytics.getContent(), "Top Revenue");
    }

    @Override
    public List<ProductAnalyticsResponse> getTopRatedProducts(Pageable pageable) {
        Page<RatingResponse> analyticsPage = salesAnalyticsClient.getRatingAnalytics(pageable);
        List<RatingResponse> analytics = analyticsPage.getContent();

        List<String> productIds = analytics.stream()
                .map(RatingResponse::getProductId)
                .toList();

        Map<String, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        return analytics.stream()
                .filter(item -> productMap.containsKey(item.getProductId()))
                .map(item -> ProductAnalyticsResponse.from(productMap.get(item.getProductId()), item))
                .toList();
    }

    private Map<String, RatingResponse> fetchRatingMap(int page, int size) {
        try {
            Page<RatingResponse> ratingsPage = salesAnalyticsClient.getRatingAnalytics(PageRequest.of(page, size));
            return ratingsPage.getContent().stream()
                    .collect(Collectors.toMap(RatingResponse::getProductId, r -> r, (r1, r2) -> r1));
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    private List<ProductAnalyticsResponse> enrichProducts(List<OrderAnalyticsResponse> analytics, String type) {
        List<String> productIds = analytics.stream()
                .map(OrderAnalyticsResponse::getProductId)
                .toList();

        Map<String, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        Map<String, RatingResponse> ratingMap = fetchRatingMap(0, 100);

        return analytics.stream()
                .filter(item -> productMap.containsKey(item.getProductId()))
                .map(item -> {
                    Product product = productMap.get(item.getProductId());
                    RatingResponse rating = ratingMap.get(item.getProductId());
                    return ProductAnalyticsResponse.from(product, item, type, rating);
                })
                .toList();
    }
}
