package com.chelex.phonebook.service;

import com.chelex.phonebook.converter.PersonConverter;
import com.chelex.phonebook.domain.dto.PersonDto;
import com.chelex.phonebook.domain.entity.Person;
import com.chelex.phonebook.domain.request.PersonRequest;
import com.chelex.phonebook.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.chelex.phonebook.constant.ErrorConstants.CANNOT_FIND_PERSON_WITH_NAME_FORMAT;
import static com.chelex.phonebook.constant.ErrorConstants.CANNOT_FIND_PERSON_WITH_UUID_FORMAT;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public List<PersonDto> getAll() {
        return personConverter.convert(personRepository.findAll());
    }

    public PersonDto getPersonByFirstName(String firstName) {
        Person person = personRepository.getReferenceByFirstName(firstName)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format(CANNOT_FIND_PERSON_WITH_NAME_FORMAT, firstName)));

        return personConverter.convert(person);
    }

    public PersonDto createPerson(PersonRequest person) {
        Person savedPerson = personRepository.save(personConverter.convert(person));
        return personConverter.convert(savedPerson);
    }

    public PersonDto getPersonByUuid(String uuid) {
        Person person = personRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format(CANNOT_FIND_PERSON_WITH_UUID_FORMAT, uuid)));
        return personConverter.convert(person);
    }
}
