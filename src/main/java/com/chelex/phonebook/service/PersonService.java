package com.chelex.phonebook.service;

import com.chelex.phonebook.converter.PersonConverter;
import com.chelex.phonebook.domain.dto.PersonDto;
import com.chelex.phonebook.domain.entity.Person;
import com.chelex.phonebook.domain.request.PersonRequest;
import com.chelex.phonebook.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public PersonDto getPerson(String firstName) {
        Person person = personRepository.getReferenceByFirstName(firstName);
        personConverter.convert(person);
        return personConverter.convert(person);
    }

    public PersonDto createPerson(PersonRequest person) {
        Person savedPerson = personRepository.save(personConverter.convert(person));
        return personConverter.convert(savedPerson);
    }
}
