package com.api.integration.hubspot.service;

import com.api.integration.hubspot.dto.request.UpdateWebhookSettingsDtoRequest;
import com.api.integration.hubspot.exceptions.ServiceUnavailableException;
import com.api.integration.hubspot.models.Throttling;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
public class WebhookService {

    @Value(value = "${api.hubspot.webhook_url}")
    private String webhookUrl;

    @Value(value = "${api.hubspot.appId}")
    private String appId;

    public String updateWebhookUrl(UpdateWebhookSettingsDtoRequest updateWebhookSettingsDtoRequest) throws ServiceUnavailableException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = getWebhookUrl() + "/" + getAppId() + "/settings";

        Throttling throttling = new Throttling(0);
        updateWebhookSettingsDtoRequest.setThrottling(throttling);

        HttpEntity entity = new HttpEntity(updateWebhookSettingsDtoRequest, headers);
        new RestTemplate().exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
        );

        return "Webhook Settings updated successfully.";
    }
}
