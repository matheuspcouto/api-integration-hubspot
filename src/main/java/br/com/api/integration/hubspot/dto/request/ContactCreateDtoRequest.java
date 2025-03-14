package br.com.api.integration.hubspot.dto.request;

import br.com.api.integration.hubspot.domain.models.Association;
import br.com.api.integration.hubspot.domain.models.Properties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContactCreateDtoRequest {
    @JsonIgnore
    private List<Association> associations;

    @NotNull
    @Valid
    private Properties properties;
}
