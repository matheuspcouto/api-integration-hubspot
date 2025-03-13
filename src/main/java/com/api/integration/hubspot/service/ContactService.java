package com.api.integration.hubspot.service;

import com.api.integration.hubspot.dto.request.ContactCreateDtoRequest;
import com.api.integration.hubspot.dto.response.ContactListDtoResponse;
import com.api.integration.hubspot.enums.AssociationCategory;
import com.api.integration.hubspot.exceptions.ServiceUnavailableException;
import com.api.integration.hubspot.models.Association;
import com.api.integration.hubspot.models.AssociationType;
import com.api.integration.hubspot.models.To;
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

    public void createContact(ContactCreateDtoRequest contactCreateDtoRequest) throws ServiceUnavailableException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokenService.getAccessToken());

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

        contactCreateDtoRequest.setAssociations(associations);

        HttpEntity entity = new HttpEntity(contactCreateDtoRequest, headers);
        ResponseEntity<String> response = new RestTemplate().exchange(
                getUrlContacts(),
                HttpMethod.POST,
                entity,
                String.class
        );

        //return response.getBody();
    }
}
