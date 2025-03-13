package com.api.integration.hubspot.exceptions;

public class RateLimitsException extends RuntimeException {
    public RateLimitsException(String message) {
        super("Too many requests: " + message);
    }
}
