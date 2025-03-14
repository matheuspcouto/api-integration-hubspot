package com.api.integration.hubspot.controller;

import com.api.integration.hubspot.client.WebhookClient;
import com.api.integration.hubspot.dto.request.UpdateWebhookSettingsDtoRequest;
import com.api.integration.hubspot.service.WebhookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/webhooks")
public class WebhookController implements WebhookClient {

    @Autowired
    private WebhookService webhookService;

    @Override
    public String updateWebhooksSettings(UpdateWebhookSettingsDtoRequest request) {
        log.info("Updating webhooks settings...");
        String response = webhookService.updateWebhookUrl(request);
        log.info("Webhooks settings updated successfully.");
        return response;
    }
}
