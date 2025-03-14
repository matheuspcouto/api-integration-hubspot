package br.com.api.integration.hubspot.domain.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class To {
    @NotBlank
    private String id = "string";
}
