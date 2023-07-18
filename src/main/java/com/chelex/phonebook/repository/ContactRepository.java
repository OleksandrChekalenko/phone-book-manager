package com.chelex.phonebook.repository;

import com.chelex.phonebook.domain.entity.Contact;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Optional<List<Contact>> getContactsByPersonId(Long id);

    Optional<List<Contact>> getContactsByPersonUuid(String uuid);

    List<Contact> findAll(@Nullable Specification<Contact> specification);
}
