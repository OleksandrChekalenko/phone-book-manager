package com.chelex.phonebook.repository;

import com.chelex.phonebook.domain.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

@DataJpaTest
//@TestPropertySource(
//        locations = {"classpath:application.properties",
//                "classpath:test.properties"})
class PersonRepositoryTest {

    private static final String VALID_TEST_FIRST_NAME = "Test";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void getReferenceByFirstName_NotExistedFirstName_ShouldReturnNothing() {
        Optional<Person> test = personRepository.getReferenceByFirstName(VALID_TEST_FIRST_NAME);
        Assertions.assertFalse(test.isPresent());
    }

    @Test
    void getReferenceByFirstName_NotExistedFirstName_ShouldSuccess() {
        entityManager.persist(Person.builder().firstName(VALID_TEST_FIRST_NAME).build());

        Optional<Person> test = personRepository.getReferenceByFirstName(VALID_TEST_FIRST_NAME);
        Assertions.assertEquals(VALID_TEST_FIRST_NAME, test.orElseThrow().getFirstName());
    }
}
