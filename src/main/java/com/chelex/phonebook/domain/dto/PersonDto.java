package com.chelex.phonebook.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class PersonDto {

    private String firstName;
    private String lastName;
    private Date createdAt;
    private Date updatedAt;
}
