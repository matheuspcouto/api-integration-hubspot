package com.api.integration.hubspot.controller;

import com.api.integration.hubspot.client.AuthClient;
import com.api.integration.hubspot.service.AuthService;
import com.api.integration.hubspot.service.TokenService;
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
    public String getAutenticationUrl() {
        log.info("Gerando url de autenticacao do Hubspot");
        String url = authService.getAutenticationUrl();
        log.info("Url de autenticacao gerada com sucesso: {}", url);
        return url;
    }

    @Override
    public String oauthCallback(String code) {
        log.info("Gerando tokens de acesso ao Hubspot");
        authService.oauthCallback(code);
        log.info("Tokens de acesso gerados com sucesso");
        return "<h1>Tokens de acesso gerados com sucesso. Agora você pode fechar esta pagina.</h1>";
    }
}
