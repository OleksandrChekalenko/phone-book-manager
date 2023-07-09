package com.chelex.phonebook.converter;

import com.chelex.phonebook.domain.dto.PersonDto;
import com.chelex.phonebook.domain.entity.Person;
import com.chelex.phonebook.domain.request.PersonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonConverter {

    private final ContactConverter contactConverter;


    public PersonDto convert(Person person) {
        if (person == null) {
            return null;
        }

        return PersonDto.builder()
                .uuid(person.getUuid())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .contacts(contactConverter.convert(person.getContacts()))
                .createdAt(person.getCreatedAt())
                .updatedAt(person.getUpdatedAt())
                .build();
    }

    public List<PersonDto> convert(List<Person> persons) {
        return persons.stream().map(this::convert).collect(Collectors.toList());
    }

    public Person convert(PersonRequest personRequest) {
        return Person.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
