package com.chelex.phonebook.service;

import com.chelex.phonebook.constant.CacheConstant;
import com.chelex.phonebook.converter.ContactConverter;
import com.chelex.phonebook.domain.dto.ContactDto;
import com.chelex.phonebook.domain.entity.Contact;
import com.chelex.phonebook.domain.request.ContactSearchCriteria;
import com.chelex.phonebook.repository.ContactRepository;
import com.chelex.phonebook.repository.specification.ContactSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.chelex.phonebook.constant.ErrorConstants.CANNOT_FIND_CONTACT_WITH_UUID_FORMAT;
import static com.chelex.phonebook.constant.ErrorConstants.CANNOT_FIND_PERSON_CONTACT_FORMAT;

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

    // use @CacheEvict(value = CacheConstant.ALL_PERSONS_CONTACTS, key = "#uuid")
    // to drop cash by uuid if Persons contact updated
    @Cacheable(value = CacheConstant.ALL_PERSONS_CONTACTS, key = "#uuid")
    public List<ContactDto> getPersonContactsByUuid(String uuid) {
        List<Contact> contact = contactRepository.getContactsByPersonUuid(uuid)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format(CANNOT_FIND_PERSON_CONTACT_FORMAT, uuid)));

        return contactConverter.convert(contact);
    }

    public List<ContactDto> searchContactsByCriteria(ContactSearchCriteria contactSearchCriteria) {
        Specification<Contact> specification =
                Specification.where(contactSpecification.personUuid(contactSearchCriteria.getPersonUuid()))
                .and(contactSpecification.nameLike(contactSearchCriteria.getFirstName()));
        List<Contact> all = contactRepository.findAll(specification);
        return contactConverter.convert(all);
    }

    public void deletePersonByUuid(String uuid) {
        Contact contact = contactRepository.findByUuid(uuid).orElseThrow(() ->
              new EntityNotFoundException(
                    String.format(CANNOT_FIND_CONTACT_WITH_UUID_FORMAT, uuid)));
        contactRepository.delete(contact);
    }
}
