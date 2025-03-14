package com.api.integration.hubspot.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth - 1° Passo", description = "API de Autorizacao")
public interface AuthClient {

    @PostMapping("/authorize")
    @Operation(summary = "Gerar Url de Autenticação do Hubspot", description = "Retorna a url de authenticação do Hubspot que deve ser utilizado para gerar os tokens de acesso. " +
            "Após receber a url, copie-a e cole em uma nova aba do navegador, faça o login ou crie a conta na Hubspot para receber os tokens de acesso.")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content)
    String getAutenticationUrl();

    @GetMapping("/oauth-callback")
    @Operation(hidden = true)
    String oauthCallback(@RequestParam("code") @Valid String code);
}
