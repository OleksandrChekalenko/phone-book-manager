package com.chelex.phonebook.service;

import com.chelex.phonebook.converter.ContactConverter;
import com.chelex.phonebook.domain.dto.ContactDto;
import com.chelex.phonebook.domain.entity.Contact;
import com.chelex.phonebook.domain.request.ContactSearchCriteria;
import com.chelex.phonebook.repository.ContactRepository;
import com.chelex.phonebook.repository.specification.ContactSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactConverter contactConverter;
    private final ContactSpecification contactSpecification;


    public List<ContactDto> getPersonContacts(Long id) {
        List<Contact> contact = contactRepository.getContactsByPersonId(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Cannot find persons: %s contacts.", id)));

        return contactConverter.convert(contact);
    }

    public List<ContactDto> getPersonContactsByUuid(String uuid) {
        List<Contact> contact = contactRepository.getContactsByPersonUuid(uuid)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    String.format("Cannot find persons: %s contacts.", uuid)));

        return contactConverter.convert(contact);
    }

    public List<ContactDto> searchContactsByCriteria(ContactSearchCriteria contactSearchCriteria) {
        Specification<Contact> specification = Specification.where(contactSpecification.personUuid(contactSearchCriteria.getPersonUuid()))
          .and(contactSpecification.nameLike(contactSearchCriteria.getFirstName()));
        List<Contact> all = contactRepository.findAll(specification);
        return contactConverter.convert(all);
    }
}
