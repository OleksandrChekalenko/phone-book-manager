package com.chelex.phonebook.repository;

import com.chelex.phonebook.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> getReferenceByFirstName(String firstName);

    Optional<Person> findByUuid(String uuid);
}
