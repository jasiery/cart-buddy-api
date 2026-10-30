package com.cartbuddy.api.services;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    ResponseEntity<?> createCart(int budgetId);

    ResponseEntity<?> getCart(int budgetGroupId);

    ResponseEntity<?> addProductToCart(int cartId, int productId);

}
