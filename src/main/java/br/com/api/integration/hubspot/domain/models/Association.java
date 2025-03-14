package br.com.api.integration.hubspot.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class Association {
    @JsonProperty("types")
    @NotEmpty
    private List<AssociationType> types;

    @NotNull
    private To to;
}
