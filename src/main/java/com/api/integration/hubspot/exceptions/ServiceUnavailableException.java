package com.api.integration.hubspot.exceptions;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super("Serviço indisponível: " + message);
    }
}
