package com.api.integration.hubspot.dto.request;

import com.api.integration.hubspot.models.Association;
import com.api.integration.hubspot.models.Properties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContactCreateDtoRequest {
    //TODO: Estudar e verificar se esse campo é necessário
    @JsonIgnore
    private List<Association> associations;

    @NotNull
    private Properties properties;
}
