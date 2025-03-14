package br.com.api.integration.hubspot.resource.exception;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super("Serviço indisponível: " + message);
    }
}
