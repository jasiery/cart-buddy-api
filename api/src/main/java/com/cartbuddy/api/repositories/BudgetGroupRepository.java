package com.cartbuddy.api.repositories;

import com.cartbuddy.api.entities.BudgetGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetGroupRepository extends JpaRepository<BudgetGroup, String> {
}
