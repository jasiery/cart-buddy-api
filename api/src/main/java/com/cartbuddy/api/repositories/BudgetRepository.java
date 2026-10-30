package com.cartbuddy.api.repositories;

import com.cartbuddy.api.entities.Budget;
import com.cartbuddy.api.entities.BudgetGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> getAllByBudgetGroupId(Long budgetGroupIId);

    Optional<Budget> findByBudgetGroupIdAndNameIgnoreCase(Long budgetGroupId, String name);

    boolean existsByBudgetGroupIdAndNameIgnoreCaseAndIdNot(
            Long budgetGroupId,
            String name,
            Long id
    );
}
