package br.com.api.integration.hubspot.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AssociationCategory {
    HUBSPOT_DEFINED(1, "HUBSPOT_DEFINED"),
    USER_DEFINED(2, "USER_DEFINED"),
    INTEGRATOR_DEFINED(3, "INTEGRATOR_DEFINED");

    private final Integer code;
    private final String description;
}
