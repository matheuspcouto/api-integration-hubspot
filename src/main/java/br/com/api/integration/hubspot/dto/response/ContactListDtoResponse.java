package br.com.api.integration.hubspot.dto.response;

import br.com.api.integration.hubspot.domain.models.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContactListDtoResponse {

    @JsonProperty("results")
    @NotNull
    private List<Contact> results;
}
