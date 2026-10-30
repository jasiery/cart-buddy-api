package com.cartbuddy.api.controllers;


import com.cartbuddy.api.requests.ProductRequest;
import com.cartbuddy.api.services.ProductService;
import com.cartbuddy.api.services.productService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable int productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable int productId,
                                               @Valid @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

}
