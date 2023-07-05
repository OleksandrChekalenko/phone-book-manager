package com.chelex.phonebook.repository;

import com.chelex.phonebook.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person getReferenceByFirstName(String firstName);
}
