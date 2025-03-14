package br.com.api.integration.hubspot.resource.exception;

public class RateLimitsException extends RuntimeException {
    public RateLimitsException(String message) {
        super("Too many requests: " + message);
    }
}
