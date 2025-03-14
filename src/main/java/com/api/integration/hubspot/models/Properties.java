package com.api.integration.hubspot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Properties {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @JsonProperty("firstname")
    private String firstName;

    @NotBlank
    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("hs_object_id")
    @JsonIgnore
    private String hsObjectId;

    @JsonProperty("createdate")
    @JsonIgnore
    private String createDate;

    @JsonProperty("lastmodifieddate")
    @JsonIgnore
    private String lastModifiedDate;
}