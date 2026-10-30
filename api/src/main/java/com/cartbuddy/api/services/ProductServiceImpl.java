package com.cartbuddy.api.services;

import com.cartbuddy.api.common.error.handling.ApiException;
import com.cartbuddy.api.common.error.handling.ErrorCode;
import com.cartbuddy.api.entities.Product;
import com.cartbuddy.api.repositories.ProductRepository;
import com.cartbuddy.api.requests.ProductRequest;
import com.cartbuddy.api.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<?> createProduct(ProductRequest productRequest) {
        productRepository.findByNameAndBrand
                (productRequest.getName(),
                        productRequest.getBrand())
                .ifPresent(product -> {
                    throw new ApiException(ErrorCode.CONFLICT, "Product already exist");
                });
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setBrand(productRequest.getBrand());
            product.setBrand(productRequest.getBrand());
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(new SuccessResponse(
                    savedProduct.getId(), "Created product successfully"));

    }

    @Override
    public ResponseEntity<?> getProductList() {
        return null;
    }

    @Override
    public ResponseEntity<?> getProduct(int productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateProduct(int productId, ProductRequest productRequest) {
        return null;
    }
}
