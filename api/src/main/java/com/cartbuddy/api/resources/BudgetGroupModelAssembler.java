package com.cartbuddy.api.resources;

import com.cartbuddy.api.controllers.BudgetGroupController;
import com.cartbuddy.api.entities.Budget;
import com.cartbuddy.api.entities.BudgetGroup;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.servlet.function.RequestPredicates.method;

@Component
public class BudgetGroupModelAssembler
        implements RepresentationModelAssembler<BudgetGroup, BudgetGroupResource> {

    @Override
    public BudgetGroupResource toModel(BudgetGroup budgetGroup) {
        BudgetGroupResource resource = new BudgetGroupResource(
                Math.toIntExact(budgetGroup.getId()),
                budgetGroup.getName());
        resource.add(
                linkTo(methodOn(BudgetGroupController.class)
                        .getBudgetGroup(Math.toIntExact(budgetGroup.getId())))
                        .withSelfRel()
        );
        return resource;
    }
}
