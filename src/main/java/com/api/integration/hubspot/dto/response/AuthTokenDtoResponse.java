package com.api.integration.hubspot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AuthTokenDtoResponse {

    @JsonProperty("access_token")
    @NotBlank
    private String accessToken;

    @JsonProperty("refresh_token")
    @NotBlank
    private String refreshToken;

    @JsonProperty("expires_in")
    @NotBlank
    private Long expiresIn;
}
