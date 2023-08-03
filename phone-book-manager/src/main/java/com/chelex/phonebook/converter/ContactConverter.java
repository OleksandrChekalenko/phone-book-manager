package com.chelex.phonebook.converter;

import com.chelex.phonebook.domain.dto.ContactDto;
import com.chelex.phonebook.domain.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactConverter {

    private final AddressConverter addressConverter;

    public List<ContactDto> convert(List<Contact> contacts) {
        return contacts.stream()
                .map(this::convert)
                .toList();
    }

    public ContactDto convert(Contact contact) {
        if (contact == null) {
            return null;
        }

        return ContactDto.builder()
                .uuid(contact.getUuid())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .address(addressConverter.convert(contact.getAddress()))
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}
