package com.chelex.phonebook.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private Date createdAt;
    private Date updatedAt;
}
