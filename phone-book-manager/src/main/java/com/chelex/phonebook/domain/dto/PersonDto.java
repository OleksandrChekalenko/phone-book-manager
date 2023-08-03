package com.chelex.phonebook.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class PersonDto {

    private String uuid;
    private String firstName;
    private String lastName;
    private Date createdAt;
    private Date updatedAt;
    private List<ContactDto> contacts;
}
