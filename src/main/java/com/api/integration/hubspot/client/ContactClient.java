package com.api.integration.hubspot.client;

import com.api.integration.hubspot.dto.request.ContactCreateDtoRequest;
import com.api.integration.hubspot.dto.response.ContactListDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Contact - 2° Passo", description = "API de Contatos")
public interface ContactClient {

    @GetMapping("/list")
    @Operation(summary = "Trazer uma lista de contatos", description = "Traz uma lista de contatos do HubSpot. " +
            "É necessário informar o token de autorização recebido no 1° passo.")
    @ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContactListDtoResponse.class))
            })
    ContactListDtoResponse getListContacts();

    @PostMapping("/create")
    @Operation(summary = "Criar um novo contato", description = "Cria um novo contato no HubSpot. " +
            "É necessário informar o token de autorização recebido no 1° passo.")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content)
    String createContact(
            @RequestBody @Valid ContactCreateDtoRequest contactCreateDtoRequest
    );
}
