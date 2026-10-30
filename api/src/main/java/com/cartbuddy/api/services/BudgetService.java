package com.cartbuddy.api.services;

import com.cartbuddy.api.requests.BudgetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BudgetService {

    ResponseEntity<?> createBudget(int budgetGroupId, BudgetRequest budgetRequest);

    ResponseEntity<?> getBudgetList(int budgetGroupId);

    ResponseEntity<?> getBudget(int budgetId);

    ResponseEntity<?> updateBudget(int budgetId, BudgetRequest budgetRequest);
}
