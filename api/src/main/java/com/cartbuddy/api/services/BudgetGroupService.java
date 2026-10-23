package com.cartbuddy.api.services;

import com.cartbuddy.api.requests.BudgetGroupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BudgetGroupService {
    ResponseEntity<?> createBudgetGroup(BudgetGroupRequest budgetGroupRequest);
}
