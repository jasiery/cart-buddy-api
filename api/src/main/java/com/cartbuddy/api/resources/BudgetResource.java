package com.cartbuddy.api.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BudgetResource extends RepresentationModel<BudgetResource> {

    private int id;

    private String name;

    private BigDecimal amount;

    private String currencyCode;

    private String statusCode;


}
