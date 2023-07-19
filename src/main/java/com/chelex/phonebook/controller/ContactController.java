package com.chelex.phonebook.controller;

import com.chelex.phonebook.domain.dto.ContactDto;
import com.chelex.phonebook.domain.request.ContactSearchCriteria;
import com.chelex.phonebook.service.ContactService;
import com.chelex.phonebook.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<SuccessResponse<List<ContactDto>>> getPersonContactById(
            @RequestParam(value = "person-id") Long personId) {

        return ResponseEntity.ok(SuccessResponse.<List<ContactDto>>builder()
                .data(contactService.getPersonContacts(personId))
                .build());
    }

    @GetMapping("/contacts/{personUuid}/get")
    public ResponseEntity<SuccessResponse<List<ContactDto>>> getPersonContactsByUuid(
            @PathVariable @UUID String personUuid) {

        return ResponseEntity.ok(SuccessResponse.<List<ContactDto>>builder()
                .data(contactService.getPersonContactsByUuid(personUuid))
                .build());
    }

    @GetMapping("/contacts/search/{personUuid}")
    public ResponseEntity<SuccessResponse<List<ContactDto>>> searchContacts(
            @PathVariable @UUID String personUuid,
            @RequestParam(value = "first-name", required = false) String firstName,
            @RequestParam(value = "last-name", required = false) String lastName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String email) {
        ContactSearchCriteria contactSearchCriteria = ContactSearchCriteria.builder()
                .personUuid(personUuid)
                .firstName(firstName)
                .lastName(lastName)
                .city(city)
                .email(email)
                .build();

        List<ContactDto> contacts = contactService.searchContactsByCriteria(contactSearchCriteria);

        return ResponseEntity.ok(SuccessResponse.<List<ContactDto>>builder().data(contacts).build());
    }
}
