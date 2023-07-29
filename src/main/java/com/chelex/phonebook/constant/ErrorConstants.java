package com.chelex.phonebook.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String CANNOT_FIND_PERSON_CONTACT_FORMAT  = "Cannot find person`s: %s contacts.";
    public static final String CANNOT_FIND_CONTACT_WITH_UUID_FORMAT  = "Cannot find contact with UUID: %s.";
    public static final String CANNOT_FIND_PERSON_WITH_NAME_FORMAT  = "Person with first name: %s does not exist.";
    public static final String CANNOT_FIND_PERSON_WITH_UUID_FORMAT  = "Person with uuid: %s does not exist.";
}
