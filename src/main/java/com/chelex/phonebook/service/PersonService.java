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

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public List<PersonDto> getAll() {
        return personConverter.convert(personRepository.findAll());
    }

    public PersonDto getPerson(String firstName) {
        Person person = personRepository.getReferenceByFirstName(firstName)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Person with first name: %s does not exist.", firstName)));

        return personConverter.convert(person);
    }

    public PersonDto getPersonByUuid(String uuid) {
        Person person = personRepository.findByUuid(uuid).orElseThrow(() ->
                new EntityNotFoundException(
                        String.format("Person with uuid: %s does not exist.", uuid)));

        return personConverter.convert(person);
    }

    public PersonDto createPerson(PersonRequest person) {
        Person savedPerson = personRepository.save(personConverter.convert(person));
        return personConverter.convert(savedPerson);
    }

    public PersonDto getPersonByUuid(String uuid) {
        Person person = personRepository.findByUuid(uuid)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Person with uuid: %s does not exist.", uuid)));
        return personConverter.convert(person);
    }
}
