package com.cartbuddy.api.controllers;


import com.cartbuddy.api.requests.BudgetGroupRequest;
import com.cartbuddy.api.requests.BudgetRequest;
import com.cartbuddy.api.services.BudgetGroupService;
import com.cartbuddy.api.services.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/create/{budgetGroupId}")
    public ResponseEntity<?> createBudget(
            @PathVariable int budgetGroupId,
            @Valid @RequestBody BudgetRequest budgetRequest) {
        return budgetService.createBudget(budgetGroupId, budgetRequest);
    }

    @GetMapping("/list/{budgetGroupId}")
    public ResponseEntity<?> getBudgetList(@PathVariable int budgetGroupId) {
        return budgetService.getBudgetList(budgetGroupId);
    }

    @GetMapping("/get/{budgetId}")
    public ResponseEntity<?> getBudget(@PathVariable int budgetId) {
        return budgetService.getBudget(budgetId);
    }

    @PutMapping("/update/{budgetId}")
    public ResponseEntity<?> updateBudgetGroup(@PathVariable int budgetId,
                                               @Valid @RequestBody BudgetRequest budgetRequest) {
        return budgetService.updateBudget(budgetId, budgetRequest);
    }

}
