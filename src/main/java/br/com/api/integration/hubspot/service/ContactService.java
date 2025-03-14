package br.com.api.integration.hubspot.service;

import br.com.api.integration.hubspot.domain.enums.AssociationCategory;
import br.com.api.integration.hubspot.domain.models.Association;
import br.com.api.integration.hubspot.domain.models.AssociationType;
import br.com.api.integration.hubspot.domain.models.To;
import br.com.api.integration.hubspot.dto.request.ContactCreateDtoRequest;
import br.com.api.integration.hubspot.dto.response.ContactListDtoResponse;
import br.com.api.integration.hubspot.resource.exception.ServiceUnavailableException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Getter
public class ContactService {

    @Value(value = "${api.hubspot.contacts_url}")
    private String urlContacts;

    @Autowired
    private TokenService tokenService;

    public ContactListDtoResponse getListContacts() throws ServiceUnavailableException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokenService.getAccessToken());

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<ContactListDtoResponse> response = new RestTemplate().exchange(
                getUrlContacts(),
                HttpMethod.GET,
                entity,
                ContactListDtoResponse.class
        );

        return response.getBody();
    }

    public void createContact(ContactCreateDtoRequest request) throws ServiceUnavailableException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokenService.getAccessToken());

        request.setAssociations(createAssociations());

        HttpEntity entity = new HttpEntity(request, headers);
        new RestTemplate().exchange(
                getUrlContacts(),
                HttpMethod.POST,
                entity,
                String.class
        );
    }

    // TODO: Método para criar a lista de associations necessária para a criação do contato
    private List<Association> createAssociations() {
        // AssociationType
        ArrayList<AssociationType> associationTypes = new ArrayList<>();
        associationTypes.add(new AssociationType(AssociationCategory.HUBSPOT_DEFINED, 0));

        // To
        Random random = new Random();
        Integer randomNumber = random.nextInt(10000);
        To to = new To(randomNumber.toString());

        // Association
        Association association = new Association(associationTypes, to);
        List<Association> associations = new ArrayList<>();
        associations.add(association);

        return associations;
    }
}