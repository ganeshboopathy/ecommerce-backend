package com.product.product_service_api.repository;

import com.product.product_service_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findTopByOrderByIdDesc();

    List<Product> findAllByIsTopRatedTrue();
}
