package br.com.api.integration.hubspot.domain.controller;

import br.com.api.integration.hubspot.domain.client.AuthClient;
import br.com.api.integration.hubspot.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController implements AuthClient {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<String> generateAuthenticationUrl() {
        log.info("Gerando url de autenticacao do Hubspot");
        String url = authService.generateAuthenticationUrl();
        log.info("Url de autenticacao gerada com sucesso: {}", url);
        return ResponseEntity.ok(url);
    }

    @Override
    public ResponseEntity<String> handleOauthCallback(String code) {
        log.info("Gerando tokens de acesso ao Hubspot");
        authService.handleOauthCallback(code);
        log.info("Tokens de acesso gerados com sucesso");
        return ResponseEntity.ok("<h1>Tokens de acesso gerados com sucesso. <br> Agora você pode fechar esta pagina e seguir os próximos passos.</h1>");
    }
}
