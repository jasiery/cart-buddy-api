package com.cartbuddy.api.services;

import com.cartbuddy.api.repositories.BudgetGroupRepository;
import com.cartbuddy.api.requests.BudgetGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BudgetGroupServiceImpl implements BudgetGroupService {

    @Autowired
    private BudgetGroupRepository budgetGroupRepository;

    @Override
    public ResponseEntity<?> createBudgetGroup(BudgetGroupRequest budgetGroupRequest) {
        return null;
    }
}
