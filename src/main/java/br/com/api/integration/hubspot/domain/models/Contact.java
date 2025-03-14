package br.com.api.integration.hubspot.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Contact {
    @NotBlank
    private String id;

    @NotNull
    private Properties properties;

    @NotBlank
    private String createdAt;

    @NotBlank
    private String updatedAt;

    @NotNull
    private boolean archived;
}