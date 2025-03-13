package com.api.integration.hubspot.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Throttling {

    @NotBlank
    private Integer maxConcurrentRequests = 0;
}
