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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactController implements ContactClient {

    @Autowired
    private ContactService contactService;

    private final Bucket bucket;

    public ContactController() {
        Bandwidth limit = Bandwidth.classic(110, Refill.greedy(110, Duration.ofSeconds(10)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @Override
    public ContactListDtoResponse getListContacts() {
        log.info("Trazendo lista de contatos do HubSpot...");
        ContactListDtoResponse response = contactService.getListContacts();
        log.info("{} Contatos do Hubspot recuperados com sucesso", response.getResults().size());
        return response;
    }

    @Override
    public String createContact(ContactCreateDtoRequest request) {
        log.info("Criando contato no HubSpot...");

        if (bucket.tryConsume(1)) {
            contactService.createContact(request);
            log.info("Contato criado com sucesso no HubSpot");
            return "Contato criado com sucesso no HubSpot";
        }
        throw new RateLimitsException("Limite de requisições excedido. Aguarde um minuto para continuar.");
    }
}
