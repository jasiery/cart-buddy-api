package com.cartbuddy.api.services;

import com.cartbuddy.api.common.error.handling.ApiException;
import com.cartbuddy.api.common.error.handling.ErrorCode;
import com.cartbuddy.api.entities.BudgetGroup;
import com.cartbuddy.api.repositories.BudgetGroupRepository;
import com.cartbuddy.api.requests.BudgetGroupRequest;
import com.cartbuddy.api.resources.BudgetGroupModelAssembler;
import com.cartbuddy.api.resources.BudgetGroupResource;
import com.cartbuddy.api.responses.SuccessResponse;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertFalseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetGroupServiceImpl implements BudgetGroupService {

    @Autowired
    private BudgetGroupRepository budgetGroupRepository;

    @Autowired
    private BudgetGroupModelAssembler budgetGroupModelAssembler;

    @Autowired
    private UserService userService;
    private AssertFalseValidator assertFalseValidator;

    @Override
    public ResponseEntity<?> createBudgetGroup(BudgetGroupRequest budgetGroupRequest) {
        budgetGroupRepository
                .findByUserIdAndNameIgnoreCase(
                        userService.getCurrentUserId(),
                        budgetGroupRequest.getName())
                .ifPresent(group -> {
                    throw new ApiException(ErrorCode.CONFLICT, "Budget group already exists");
                });

        BudgetGroup budgetGroup = new BudgetGroup();
        budgetGroup.setUserId(userService.getCurrentUserId());
        budgetGroup.setName(budgetGroupRequest.getName());
        BudgetGroup savedBudgetGroup = budgetGroupRepository.save(budgetGroup);
        return ResponseEntity.ok(new SuccessResponse(savedBudgetGroup.getId(), "Budget group created successfully"));

    }

    @Override
    public ResponseEntity<?> getBudgetGroupList() {
        List<BudgetGroupResource> resources = budgetGroupRepository
                .getAllByUserId(userService.getCurrentUserId())
                .stream()
                .map(budgetGroupModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @Override
    public ResponseEntity<?> getBudgetGroup(int budgetGroupId) {
        BudgetGroup existingBudgetGroup = budgetGroupRepository.findById((long) budgetGroupId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Budget Group not found"));
        BudgetGroupResource resource = budgetGroupModelAssembler.toModel(existingBudgetGroup);
        return ResponseEntity.ok(resource);
    }

    @Override
    public ResponseEntity<?> updateBudgetGroup(int budgetGroupId, BudgetGroupRequest budgetGroupRequest) {
        BudgetGroup existingBudgetGroup = budgetGroupRepository.findById((long) budgetGroupId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Budget Group not found"));
        boolean nameExistsOnOtherBudgetGroupOfUser = budgetGroupRepository.existsByUserIdAndNameIgnoreCaseAndIdNot
                (userService.getCurrentUserId(), budgetGroupRequest.getName(), existingBudgetGroup.getId());
        if(nameExistsOnOtherBudgetGroupOfUser) {
            throw new ApiException(ErrorCode.CONFLICT, "Budget Group name exists already");
        }
        existingBudgetGroup.setName(budgetGroupRequest.getName());
        budgetGroupRepository.save(existingBudgetGroup);
        return ResponseEntity.ok(new SuccessResponse(existingBudgetGroup.getId(), "Budget group updated successfully"));
    }

}
