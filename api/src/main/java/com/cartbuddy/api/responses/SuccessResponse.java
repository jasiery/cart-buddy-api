package com.cartbuddy.api.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {
    private String message;
    private Integer id;

    public SuccessResponse(String message) {
        this.message = message;
    }

    public SuccessResponse(Long id, String message) {
        this.id = Math.toIntExact(id);
        this.message = message;
    }

}
