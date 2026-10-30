package com.cartbuddy.api.common.error.handling;

import com.cartbuddy.api.common.util.DateTimeUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApi(ApiException ex, HttpServletRequest req) {
        return build(ex.getCode(), ex.getMessage(), null, req);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleConflict(Exception ex, HttpServletRequest req) {
        return build(ErrorCode.CONFLICT, "Resource conflict, duplicated resource", null, req);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuth(Exception ex, HttpServletRequest req) {
        return build(ErrorCode.UNAUTHORIZED, "Invalid username or password, invalid token", null, req);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(
            Exception ex,
            HttpServletRequest req) {

        Map<String, String> errors = new HashMap<>();
        if(ex instanceof MethodArgumentNotValidException notValidException) {
            notValidException.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            return build(ErrorCode.BAD_REQUEST, "Validation Check", errors, req);
        } else {
            return build(ErrorCode.BAD_REQUEST, "Invalid request", null, req);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex, HttpServletRequest req) {
        log.error("Unhandled exception", ex);
        return build(ErrorCode.INTERNAL_ERROR, "Unexpected error", null, req);
    }
    private ResponseEntity<ErrorResponse> build(ErrorCode code, String message, Map<String, String> errors, HttpServletRequest request) {
        var body = new ErrorResponse(code.name(), message, errors, dateTimeUtil.getCurrentDateTime(), request.getRequestURI());
        return ResponseEntity.status(code.status()).body(body);
    }
}
