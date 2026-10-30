package com.cartbuddy.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetGroupResource extends RepresentationModel<BudgetGroupResource> {

    private int id;

    private String name;

    private List<BudgetResource> budgetList;

    public BudgetGroupResource(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public BudgetGroupResource(int id, String name, List<BudgetResource> budgetList) {
        this.id = id;
        this.name = name;
        this.budgetList = budgetList;
    }
}
