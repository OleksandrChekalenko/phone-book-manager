package com.chelex.phonebook.converter;

import com.chelex.phonebook.domain.dto.ContactDto;
import com.chelex.phonebook.domain.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactConverter {

    public List<ContactDto> convert(List<Contact> contacts) {
        return contacts.stream().map(this::convert).collect(Collectors.toList());
    }

    public ContactDto convert(Contact contact) {
        return ContactDto.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .city(contact.getCity())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}