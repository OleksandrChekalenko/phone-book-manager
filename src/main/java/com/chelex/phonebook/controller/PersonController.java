package com.chelex.phonebook.controller;

import com.chelex.phonebook.domain.dto.PersonDto;
import com.chelex.phonebook.domain.request.PersonRequest;
import com.chelex.phonebook.service.PersonService;
import com.chelex.phonebook.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<SuccessResponse<List<PersonDto>>> getAll() {
        return ResponseEntity.ok(SuccessResponse.<List<PersonDto>>builder().data(personService.getAll()).build());
    }

    @GetMapping("/person")
    public ResponseEntity<SuccessResponse<PersonDto>> getPersonByName(
            @RequestParam(value = "first-name") String firstName) {
        PersonDto person = personService.getPerson(firstName);
        return ResponseEntity.ok(SuccessResponse.<PersonDto>builder().data(person).build());
    }

    @GetMapping("/personUuid")
    public ResponseEntity<PersonDto> getPersonByUuid(
            @RequestParam(value = "uuid") String uuid) {
        PersonDto person = personService.getPersonByUuid(uuid);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/person")
    public ResponseEntity<SuccessResponse<PersonDto>> createPerson(@RequestBody @Valid PersonRequest person) {
        PersonDto personDto = personService.createPerson(person);
        return ResponseEntity.ok(SuccessResponse.<PersonDto>builder().data(personDto).build());
    }
}
