package com.chelex.phonebook.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class ContactDto {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date updatedAt;
}
