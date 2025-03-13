package com.api.integration.hubspot.enums;

import lombok.AllArgsConstructor;

//TODO: Adicionar mais scopes e aplicar
@AllArgsConstructor
public enum HubspotScopes {
    CONTACTS_WRITE("crm.objects.contacts.write"),
    CONTACTS_READ("crm.objects.contacts.read");

    private final String scope;
}
