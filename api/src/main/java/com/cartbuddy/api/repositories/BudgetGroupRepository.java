package com.cartbuddy.api.repositories;

import com.cartbuddy.api.entities.Budget;
import com.cartbuddy.api.entities.BudgetGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetGroupRepository extends JpaRepository<BudgetGroup, Long> {

    Optional<BudgetGroup> findByUserIdAndNameIgnoreCase(Long userId, String name);

    List<BudgetGroup> getAllByUserId(Long userId);

    BudgetGroup getBudgetGroupById(Long id);

    boolean existsByUserIdAndNameIgnoreCaseAndIdNot(
            Long userId,
            String name,
            Long id);
}
