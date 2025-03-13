package com.api.integration.hubspot.client;

import com.api.integration.hubspot.dto.request.UpdateWebhookSettingsDtoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Webhooks - 3° Passo", description = "API de Webhooks")
public interface WebhookClient {

    @PutMapping("/settings")
    @Operation(summary = "3.1 - Atualizar Configurações dos Webhooks", description = "Atualiza as configurações de webhooks do HubSpot.")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json"))
    String updateWebhooksSettings(
            @RequestBody @Valid UpdateWebhookSettingsDtoRequest updateWebhookSettingsDtoRequest
    );
}
