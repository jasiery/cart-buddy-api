package com.cartbuddy.api.services;

import com.cartbuddy.api.common.error.handling.ApiException;
import com.cartbuddy.api.common.error.handling.ErrorCode;
import com.cartbuddy.api.entities.Budget;
import com.cartbuddy.api.entities.Cart;
import com.cartbuddy.api.entities.CartProduct;
import com.cartbuddy.api.entities.Product;
import com.cartbuddy.api.repositories.BudgetRepository;
import com.cartbuddy.api.repositories.CartProductRepository;
import com.cartbuddy.api.repositories.CartRepository;
import com.cartbuddy.api.repositories.ProductRepository;
import com.cartbuddy.api.responses.SuccessResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    private final static int DEFAULT_QUANTITY = 1;
    private final static BigDecimal DEFAULT_PRICE = BigDecimal.ONE;

    public ResponseEntity<?> createCart(int budgetId) {
        Budget existingBudget  = budgetRepository.findById((long) budgetId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Budget not exist"));
        cartRepository.findByBudgetId((long) budgetId)
                .ifPresent(cart -> {
                    throw new ApiException(ErrorCode.CONFLICT, "Cart already exists");
                });
        Cart cart = new Cart();
        cart.setBudget(existingBudget);
        Cart savedCart = cartRepository.save(cart);
        return ResponseEntity.ok(new SuccessResponse(savedCart.getId(), "Cart created successfully"));
    }

    @Override
    public ResponseEntity<?> getCart(int budgetId) {
        Cart existingCart = cartRepository.findByBudgetId((long) budgetId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Cart not found"));
        return ResponseEntity.ok(existingCart);
    }

    @Override
    public ResponseEntity<?> addProductToCart(int cartId, int productId) {
        Cart existingCart = cartRepository.findByBudgetId((long) cartId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Cart not found"));
        Product existingProduct = productRepository.findById((long) cartId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Cart not found"));
        boolean productExistsInCart = cartProductRepository.existsByCartIdAndProductId((long) cartId, (long) productId);
        if(productExistsInCart) {
            throw new ApiException(ErrorCode.CONFLICT, "Product already in cart");
        }
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(existingCart);
        cartProduct.setProduct(existingProduct);
        cartProduct.setQuantity(DEFAULT_QUANTITY);
        cartProduct.setUnitPrice(DEFAULT_PRICE);
        CartProduct savedCartProduct = cartProductRepository.save(cartProduct);
        return ResponseEntity.ok(savedCartProduct);
    }
}
