package com.cartbuddy.api.controllers;

import com.cartbuddy.api.common.error.handling.ApiException;
import com.cartbuddy.api.common.error.handling.ErrorCode;
import com.cartbuddy.api.entities.Budget;
import com.cartbuddy.api.entities.Cart;
import com.cartbuddy.api.repositories.BudgetRepository;
import com.cartbuddy.api.repositories.CartRepository;
import com.cartbuddy.api.services.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<?> createCart(@PathVariable int budgetId) {
        return cartService.createCart(budgetId);
    }

    @GetMapping
    public ResponseEntity<?> getCart(@PathVariable int budgetGroupId) {
        return cartService.getCart(budgetGroupId);
    }

    @PostMapping("/{cartId}/add-product/{productId")
    public ResponseEntity<?> addProductToCart(@PathVariable
                                              int cartId,
                                              int productId) {
        return cartService.addProductToCart(cartId, productId);
    }


}