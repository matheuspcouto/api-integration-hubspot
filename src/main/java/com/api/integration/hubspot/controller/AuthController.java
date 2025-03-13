package com.api.integration.hubspot.controller;

import com.api.integration.hubspot.client.AuthClient;
import com.api.integration.hubspot.dto.request.AuthTokenDtoRequest;
import com.api.integration.hubspot.service.AuthService;
import com.api.integration.hubspot.service.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public class AuthController implements AuthClient {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @Override
    public String getAuthorizationCode(@RequestBody AuthTokenDtoRequest authTokenDtoRequest) {
        log.info("Gerando url de autenticacao do Hubspot");
        String url = authService.getAuthorizationCode(authTokenDtoRequest);
        log.info("Url de autenticacao gerada com sucesso: {}", url);
        return url;
    }

    @Override
    public String oauthCallback(@RequestParam("code") String code) {
        log.info("Gerando tokens de acesso ao Hubspot");
        authService.oauthCallback(code);
        log.info("Tokens de acesso gerados com sucesso");
        return "<h1>Tokens de acesso gerados com sucesso. Agora você pode fechar esta pagina.</h1>";
    }
}
