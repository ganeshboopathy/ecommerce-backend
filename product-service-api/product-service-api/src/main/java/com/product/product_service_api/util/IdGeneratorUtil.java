package com.product.product_service_api.util;

import com.product.product_service_api.entity.Product;
import com.product.product_service_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IdGeneratorUtil {

    private static ProductRepository productRepository;

    @Autowired
    public void init(ProductRepository Repository){
        IdGeneratorUtil.productRepository=Repository;
    }

    public  static String generateProductId() {

        Optional<Product> lastUser = productRepository.findTopByOrderByIdDesc();

        if (lastUser.isEmpty()) {
            return "NM001";
        }

        String lastId = lastUser.get().getId(); // NM007
        int number = Integer.parseInt(lastId.substring(2)); // 7
        number++;

        return String.format("NM%03d", number);
    }
}

