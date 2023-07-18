package com.chelex.phonebook.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactSearchCriteria {

    @UUID
    private String personUuid;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
}
