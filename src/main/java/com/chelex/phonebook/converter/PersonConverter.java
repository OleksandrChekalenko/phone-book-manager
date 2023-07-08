package com.chelex.phonebook.converter;

import com.chelex.phonebook.domain.dto.PersonDto;
import com.chelex.phonebook.domain.entity.Person;
import com.chelex.phonebook.domain.request.PersonRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonConverter {

    public PersonDto convert(Person person) {
        return PersonDto.builder()
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .createdAt(person.getCreatedAt())
            .updatedAt(person.getUpdatedAt())
            .build();
    }

    public List<PersonDto> convert(List<Person> persons) {
        return persons.stream().map(person -> PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .createdAt(person.getCreatedAt())
                .updatedAt(person.getUpdatedAt())
                .build()).collect(Collectors.toList());
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
