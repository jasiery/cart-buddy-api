package com.cartbuddy.api.services;

import com.cartbuddy.api.requests.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ResponseEntity<?> createProduct(ProductRequest productRequest);

    ResponseEntity<?> getProductList();

    ResponseEntity<?> getProduct(int productId);

    ResponseEntity<?> updateProduct(int productId, ProductRequest productRequest);
}
