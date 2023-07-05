package com.chelex.phonebook.domain.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class PersonRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;
    @NotNull
    @NotBlank
    @NotEmpty
    private String lastName;
}
