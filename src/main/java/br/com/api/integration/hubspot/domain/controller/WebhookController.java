package br.com.api.integration.hubspot.domain.controller;

import br.com.api.integration.hubspot.domain.client.WebhookClient;
import br.com.api.integration.hubspot.dto.request.UpdateWebhookSettingsDtoRequest;
import br.com.api.integration.hubspot.service.WebhookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/webhooks")
public class WebhookController implements WebhookClient {

    @Autowired
    private WebhookService webhookService;

    @Override
    public ResponseEntity<String> handleWebhookContactCreated(UpdateWebhookSettingsDtoRequest updateWebhookSettingsDtoRequest) {
        log.info("Recebendo notificações webhook para contact.creation...");
        log.info("Novo contato criado: {}");
        return ResponseEntity.ok("Webhook recebido com sucesso.");
    }
}
