package br.com.api.integration.hubspot.dto.request;

import br.com.api.integration.hubspot.domain.models.Throttling;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateWebhookSettingsDtoRequest {

    @JsonIgnore
    private Throttling throttling;

    @NotBlank
    private String targetUrl;
}
