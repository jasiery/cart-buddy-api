package com.cartbuddy.api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    @Size(max = 100, message = "Must not exceed 100 characters")
    private String name;

    @NotBlank
    @Size(max = 100, message = "Must not exceed 100 characters")
    private String brand;


    @NotBlank
    @Size(max = 100, message = "Must not exceed 100 characters")
    private String category;
}

