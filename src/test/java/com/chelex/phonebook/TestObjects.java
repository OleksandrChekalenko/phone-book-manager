package com.chelex.phonebook;

import com.chelex.phonebook.domain.dto.ContactDto;
import com.chelex.phonebook.domain.dto.PersonDto;

import java.util.Date;
import java.util.List;

public class TestObjects {

    public static PersonDto getPersonDto(String firstName) {
        return PersonDto.builder()
                .firstName(firstName)
                .lastName("TestLastName")
                .createdAt(new Date())
                .updatedAt(new Date())
                .contacts(List.of(getContactDto()))
                .build();
    }

    public static ContactDto getContactDto() {
        return ContactDto.builder()
                .firstName("TestContactFirstName")
                .lastName("TestContactLastName")
                .email("contact@testemail.com")
                .phoneNumber("000000000000")
                .city("Kyiv")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
