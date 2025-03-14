package com.api.integration.hubspot.client;

import com.api.integration.hubspot.dto.request.UpdateWebhookSettingsDtoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Webhooks - 3° Passo", description = "API de Webhooks")
public interface WebhookClient {

    @PostMapping("/contacts")
    @Operation(summary = "Recebe notificações webhook quando um contato é criado", description = " Recebe notificações webhook quando um contato é criado." +
            "É necessário informar o token de autorização recebido no 1° passo.")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content)
    ResponseEntity<String> handleWebhookContactCreated(
            @RequestBody UpdateWebhookSettingsDtoRequest updateWebhookSettingsDtoRequest
    );
}
