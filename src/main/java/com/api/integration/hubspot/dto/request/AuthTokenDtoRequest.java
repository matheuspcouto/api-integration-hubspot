package com.api.integration.hubspot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthTokenDtoRequest {

    @JsonProperty("client_id")
    @NotBlank
    private String clientId;

    @JsonProperty("client_secret")
    @NotBlank
    private String clientSecret;

    @JsonProperty("scope")
    @NotBlank
    private String scope;

    public AuthTokenDtoRequest(String clientId, String clientSecret, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }
}
