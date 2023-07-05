package com.chelex.PhoneBook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1)")
public class PersonController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("answer");
    }
}
