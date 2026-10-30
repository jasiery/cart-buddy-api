package com.cartbuddy.api.services;

import com.cartbuddy.api.requests.BudgetGroupRequest;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BudgetGroupService {
    ResponseEntity<?> createBudgetGroup(BudgetGroupRequest budgetGroupRequest);

    ResponseEntity<?> getBudgetGroupList();

    ResponseEntity<?> getBudgetGroup(int id);

    ResponseEntity<?> updateBudgetGroup(int id, BudgetGroupRequest budgetGroupRequest);
}
