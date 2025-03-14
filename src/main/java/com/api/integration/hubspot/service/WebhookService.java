package com.api.integration.hubspot.service;

import com.api.integration.hubspot.dto.request.UpdateWebhookSettingsDtoRequest;
import com.api.integration.hubspot.exceptions.ServiceUnavailableException;
import com.api.integration.hubspot.models.Throttling;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
public class WebhookService {

    @Value(value = "${api.hubspot.webhook.url}")
    private String webhookUrl;

    @Value(value = "${api.hubspot.webhook.target_url}")
    private String targetUrl;

    @Value(value = "${api.hubspot.appId}")
    private String appId;

    @Autowired
    private TokenService tokenService;

    public String updateWebhookUrl() throws ServiceUnavailableException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokenService.getAccessToken());

        String url = getWebhookUrl() + "/" + getAppId() + "/settings";

        Throttling throttling = new Throttling(0);
        UpdateWebhookSettingsDtoRequest request = new UpdateWebhookSettingsDtoRequest(throttling, getTargetUrl());

        HttpEntity entity = new HttpEntity(request, headers);
        new RestTemplate().exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
        );

        return "Webhook Settings updated successfully.";
    }
}
