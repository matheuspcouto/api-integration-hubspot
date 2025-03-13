package com.api.integration.hubspot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpErrors {
    UNAUTHORIZED(401, "Não autorizado"),
    FORBIDDEN(403, "Proibido"),
    NOT_FOUND(404, "Não encontrado"),
    METHOD_NOT_ALLOWED(405, "Método não permitido"),
    INTERNAL_SERVER_ERROR(500, "Erro interno do servidor"),
    SERVICE_UNAVAILABLE(503, "Serviço indisponível"),
    RATE_LIMIT_EXCEEDED(429, "Limite de taxa excedido");

    private final int code;
    private final String message;

    public static String getMessageByCode(Integer code) {
        for (HttpErrors error : HttpErrors.values()) {
            if (error.getCode() == code) {
                return error.getMessage();
            }
        }
        return null;
    }
}
