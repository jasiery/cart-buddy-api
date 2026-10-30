package com.cartbuddy.api.common.error.handling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private Map<String, String> errors;
    private String timestamp;
    private String path;
}
