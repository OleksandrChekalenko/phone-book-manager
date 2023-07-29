package com.chelex.phonebook.service;

import com.chelex.phonebook.TestObjects;
import com.chelex.phonebook.converter.PersonConverter;
import com.chelex.phonebook.domain.dto.PersonDto;
import com.chelex.phonebook.domain.entity.Person;
import com.chelex.phonebook.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.chelex.phonebook.ErrorConstants.CANNOT_FIND_PERSON_WITH_NAME_FORMAT;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonConverter personConverter;

    @InjectMocks
    private PersonService personService;

    @Nested
    @DisplayName("Get Persons")
    class GetPerson {

        private static final String VALID_FIRST_NAME = "John";
        private static final String NOT_EXISTED_FIRST_NAME = "Some not existed fist name";

        @Test
        void getPersonByFirstName_ValidData_ShouldSuccess() {
            Person person = TestObjects.getPerson(VALID_FIRST_NAME);
            when(personRepository.getReferenceByFirstName(VALID_FIRST_NAME))
                    .thenReturn(Optional.ofNullable(person));
            PersonDto personDto = TestObjects.getPersonDto(VALID_FIRST_NAME);
            when(personConverter.convert(person)).thenReturn(personDto);

            PersonDto personByFirstName = personService.getPersonByFirstName(VALID_FIRST_NAME);

            verify(personRepository).getReferenceByFirstName(VALID_FIRST_NAME);
            verify(personConverter).convert(person);

            Assertions.assertEquals(personDto, personByFirstName);
        }

        @Test
        void getPersonByFirstName_NotExistedFirstName_ShouldThrowException() {
            when(personRepository.getReferenceByFirstName(NOT_EXISTED_FIRST_NAME)).
                    thenReturn(Optional.empty());

            Exception exception = Assertions.assertThrows(EntityNotFoundException.class,
                    () -> personService.getPersonByFirstName(NOT_EXISTED_FIRST_NAME));

            verify(personRepository).getReferenceByFirstName(NOT_EXISTED_FIRST_NAME);
            verifyNoInteractions(personConverter);

            Assertions.assertEquals(String.format(CANNOT_FIND_PERSON_WITH_NAME_FORMAT, NOT_EXISTED_FIRST_NAME),
                    exception.getMessage());
        }
    }
}
