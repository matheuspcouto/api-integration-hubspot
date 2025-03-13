package com.api.integration.hubspot.handler;

import com.api.integration.hubspot.exceptions.RateLimitsException;
import com.api.integration.hubspot.exceptions.ServiceUnavailableException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ApiError apiError = new ApiError(null, ex.getMessage(), "");
        log.error(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(null, ex.getMessage(), request.getRequestURI());
        log.error(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceUnavailableException.class})
    protected ResponseEntity<ApiError> handleServiceUnavailableException(ServiceUnavailableException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE.value(), null, request.getRequestURI());
        log.error(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({RateLimitsException.class})
    protected ResponseEntity<ApiError> handleRateLimitsException(RateLimitsException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.TOO_MANY_REQUESTS.value(), null, request.getRequestURI());
        log.error(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    protected ResponseEntity<ApiError> handleHttpClientErrorException(HttpClientErrorException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(ex.getStatusCode().value(), null, request.getRequestURI());
        log.error(ex.getMessage());
        return new ResponseEntity<>(apiError, ex.getStatusCode());
    }

}
