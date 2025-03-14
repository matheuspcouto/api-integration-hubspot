package com.api.integration.hubspot.controller;

import com.api.integration.hubspot.client.ContactClient;
import com.api.integration.hubspot.dto.request.ContactCreateDtoRequest;
import com.api.integration.hubspot.dto.response.ContactListDtoResponse;
import com.api.integration.hubspot.exceptions.RateLimitsException;
import com.api.integration.hubspot.service.ContactService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController implements ContactClient {

    @Autowired
    private ContactService contactService;

    private final Bucket rateLimitBucket ;

    // TODO: Explicar que colou aqui pq não sabia se a regra é para todas as request ou somente essa request
    public ContactController() {
        Bandwidth limit = Bandwidth.classic(110, Refill.greedy(110, Duration.ofSeconds(10)));
        this.rateLimitBucket = Bucket.builder().addLimit(limit).build();
    }

    @Override
    public ContactListDtoResponse getListContacts() {
        log.info("Trazendo lista de contatos do HubSpot...");
        ContactListDtoResponse response = contactService.getListContacts();
        log.info("{} Contatos do Hubspot recuperados com sucesso", response.getResults().size());
        return response;
    }

    @Override
    public ResponseEntity<String> createContact(ContactCreateDtoRequest request) {
        log.info("Criando contato no HubSpot...");
        validateRateLimit();
        contactService.createContact(request);
        log.info("Contato criado com sucesso no HubSpot");
        return ResponseEntity.ok("Contato criado com sucesso no HubSpot");
    }

    private void validateRateLimit() {
        if (!rateLimitBucket.tryConsume(1)) {
            throw new RateLimitsException("Limite de requisições excedido. Aguarde um minuto para continuar.");
        }
    }
}
