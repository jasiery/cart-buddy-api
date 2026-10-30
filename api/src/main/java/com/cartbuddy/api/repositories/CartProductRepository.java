package com.cartbuddy.api.repositories;

import com.cartbuddy.api.entities.Cart;
import com.cartbuddy.api.entities.CartProduct;
import com.cartbuddy.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    boolean existsByCartIdAndProductId(Long cartId, Long productId);

    CartProduct cart(Cart cart);

    void deleteByCartIdAndProductId(Long cartId, Long productId);
}

