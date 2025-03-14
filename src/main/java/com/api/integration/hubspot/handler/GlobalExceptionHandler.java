package com.api.integration.hubspot.handler;

import com.api.integration.hubspot.exceptions.RateLimitsException;
import com.api.integration.hubspot.exceptions.ServiceUnavailableException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ApiError> handleGenericException(Exception ex) {
        return createErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField().toString().replace("properties.", "");
            errorMessage.append(fieldName).append(" - ").append(fieldError.getDefaultMessage()).append(" / ");
        }
        return createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
    }

    @ExceptionHandler({ServiceUnavailableException.class})
    protected ResponseEntity<ApiError> handleServiceUnavailableException(ServiceUnavailableException ex, HttpServletRequest request) {
        return createErrorResponse(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value(), request.getRequestURI());
    }

    @ExceptionHandler({RateLimitsException.class})
    protected ResponseEntity<ApiError> handleRateLimitsException(RateLimitsException ex, HttpServletRequest request) {
        return createErrorResponse(ex.getMessage(), HttpStatus.TOO_MANY_REQUESTS.value(), request.getRequestURI());
    }

    @ExceptionHandler({HttpClientErrorException.class})
    protected ResponseEntity<ApiError> handleHttpClientErrorException(HttpClientErrorException ex, HttpServletRequest request) {
        return createErrorResponse(ex.getMessage(), ex.getStatusCode().value(), request.getRequestURI());
    }

    private ResponseEntity<ApiError> createErrorResponse(String message, Integer status, String requestUri) {
        ApiError apiError = new ApiError(message, requestUri);
        log.error("Error occurred: {}", message);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(status));
    }
}
