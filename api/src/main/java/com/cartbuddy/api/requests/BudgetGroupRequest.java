package com.cartbuddy.api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BudgetGroupRequest {

    @NotBlank
    @Size(max = 100, message = "Must not exceed 100 characters")
    private String name;
}
