package com.cartbuddy.api.repositories;


import com.cartbuddy.api.entities.Budget;
import com.cartbuddy.api.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByBudgetId(Long budgetId);
}
