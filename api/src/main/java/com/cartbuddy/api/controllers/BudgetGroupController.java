package com.cartbuddy.api.controllers;

import com.cartbuddy.api.requests.BudgetGroupRequest;
import com.cartbuddy.api.services.BudgetGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget-group")
public class BudgetGroupController {

    @Autowired
    private BudgetGroupService budgetGroupService;

    @PostMapping("/create")
    public ResponseEntity<?> createBudgetGroup(@Valid @RequestBody BudgetGroupRequest budgetGroupRequest) {
        return budgetGroupService.createBudgetGroup(budgetGroupRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getBudgetGroupList() {
        return budgetGroupService.getBudgetGroupList();
    }

    @GetMapping("/get/{budgetGroupId}")
    public ResponseEntity<?> getBudgetGroup(@PathVariable int budgetGroupId) {
        return budgetGroupService.getBudgetGroup(budgetGroupId);
    }

    @PutMapping("/update/{budgetGroupId}")
    public ResponseEntity<?> updateBudgetGroup(@PathVariable int budgetGroupId,
                                                      @Valid @RequestBody BudgetGroupRequest budgetGroupRequest) {
        return budgetGroupService.updateBudgetGroup(budgetGroupId, budgetGroupRequest);
    }

}
