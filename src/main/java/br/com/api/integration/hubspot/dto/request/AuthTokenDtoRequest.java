package br.com.api.integration.hubspot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "api.hubspot.auth")
public class AuthTokenDtoRequest {

    @JsonProperty("client_id")
    @NotBlank
    private String clientId;

    @JsonProperty("client_secret")
    @NotBlank
    private String clientSecret;

    @NotBlank
    private String scope;

    @JsonProperty("redirect_uri")
    @NotBlank
    private String redirectUri;
}
