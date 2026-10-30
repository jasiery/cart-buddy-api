package com.cartbuddy.api.resources;

import com.cartbuddy.api.controllers.BudgetController;
import com.cartbuddy.api.controllers.BudgetGroupController;
import com.cartbuddy.api.entities.Budget;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BudgetModelAssembler implements RepresentationModelAssembler<Budget, BudgetResource> {

    @Override
    public BudgetResource toModel(Budget budget) {
        BudgetResource resource = new BudgetResource(
                Math.toIntExact(budget.getId()),
                budget.getName(),
                budget.getAmount(),
                budget.getCurrencyCode(),
                budget.getStatus().name());
        resource.add(
                linkTo(methodOn(BudgetController.class)
                        .getBudget(Math.toIntExact(budget.getId())))
                        .withSelfRel()
        );
        return resource;
    }
}
