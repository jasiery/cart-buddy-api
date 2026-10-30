package com.cartbuddy.api.services;

import com.cartbuddy.api.common.error.handling.ApiException;
import com.cartbuddy.api.common.error.handling.ErrorCode;
import com.cartbuddy.api.entities.Budget;
import com.cartbuddy.api.entities.BudgetGroup;
import com.cartbuddy.api.repositories.BudgetGroupRepository;
import com.cartbuddy.api.repositories.BudgetRepository;
import com.cartbuddy.api.requests.BudgetGroupRequest;
import com.cartbuddy.api.requests.BudgetRequest;
import com.cartbuddy.api.resources.BudgetGroupModelAssembler;
import com.cartbuddy.api.resources.BudgetGroupResource;
import com.cartbuddy.api.resources.BudgetModelAssembler;
import com.cartbuddy.api.resources.BudgetResource;
import com.cartbuddy.api.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetGroupRepository budgetGroupRepository;

    @Autowired
    private BudgetModelAssembler budgetModelAssembler;

    @Override
    public ResponseEntity<?> createBudget(int budgetGroupId, BudgetRequest budgetRequest) {
        budgetRepository
                .findByBudgetGroupIdAndNameIgnoreCase(
                        (long) budgetGroupId,
                        budgetRequest.getName())
                .ifPresent(group -> {
                    throw new ApiException(ErrorCode.CONFLICT, "Budget already exists");
                });
        BudgetGroup budgetGroup = budgetGroupRepository.findById((long) budgetGroupId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Budget group not exist"));
        Budget budget = new Budget();
        budget.setBudgetGroup(budgetGroup);
        budget.setName(budgetRequest.getName());
        budget.setAmount(budgetRequest.getAmount());
        budget.setCurrencyCode(budgetRequest.getCurrencyCode());

        Budget savedBudget = budgetRepository.save(budget);
        return ResponseEntity.ok(new SuccessResponse(savedBudget.getId(), "Budget created successfully"));
    }

    @Override
    public ResponseEntity<?> getBudgetList(int budgetGroupId) {
        budgetGroupRepository.findById((long) budgetGroupId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Budget group not exist"));
        List<BudgetResource> resources = budgetRepository
                .getAllByBudgetGroupId((long) budgetGroupId)
                .stream()
                .map(budgetModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @Override
    public ResponseEntity<?> getBudget(int budgetId) {
        Budget existingBudget = budgetRepository.findById((long) budgetId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Budget not found"));
        BudgetResource resource = budgetModelAssembler.toModel(existingBudget);
        return ResponseEntity.ok(resource);
    }

    @Override
    public ResponseEntity<?> updateBudget(int budgetId, BudgetRequest budgetRequest) {
        Budget existingBudget = budgetRepository.findById((long) budgetId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Budget not found"));
        boolean nameExistsOnOtherBudgetOfBudgetGroup = budgetRepository.existsByBudgetGroupIdAndNameIgnoreCaseAndIdNot
                (existingBudget.getBudgetGroup().getId(), budgetRequest.getName(), existingBudget.getId());
        if (nameExistsOnOtherBudgetOfBudgetGroup) {
            throw new ApiException(ErrorCode.CONFLICT, "Budget name exists already");
        }
        existingBudget.setName(budgetRequest.getName());
        existingBudget.setAmount(budgetRequest.getAmount());
        existingBudget.setCurrencyCode(budgetRequest.getCurrencyCode());

        budgetRepository.save(existingBudget);
        return ResponseEntity.ok(new SuccessResponse(existingBudget.getId(), "Budget group updated successfully"));
    }
}
