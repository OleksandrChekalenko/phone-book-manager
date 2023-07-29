package com.chelex.phonebook;

import com.chelex.phonebook.domain.dto.ContactDto;
import com.chelex.phonebook.domain.dto.PersonDto;
import com.chelex.phonebook.domain.entity.Person;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestObjects {

    public static String getRandomUuid() {
        return UUID.randomUUID().toString();
    }

    public static PersonDto getPersonDto(String firstName) {
        return PersonDto.builder()
                .uuid(getRandomUuid())
                .firstName(firstName)
                .lastName("TestLastName")
                .createdAt(new Date())
                .updatedAt(new Date())
                .contacts(List.of(getContactDto()))
                .build();
    }

    public static PersonDto getPersonDtoWithUuid(String uuid) {
        return PersonDto.builder()
                .uuid(uuid)
                .firstName("TestFirstName")
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

    public static Person getPerson(String firstName) {
        return Person.builder()
                .firstName(firstName)
                .build();
    }
}
