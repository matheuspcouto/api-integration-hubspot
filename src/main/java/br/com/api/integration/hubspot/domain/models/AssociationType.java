package br.com.api.integration.hubspot.domain.models;

import br.com.api.integration.hubspot.domain.enums.AssociationCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssociationType {
    @NotBlank
    private AssociationCategory associationCategory = AssociationCategory.HUBSPOT_DEFINED;

    @Min(0)
    private Integer associationTypeId = 0;
}
