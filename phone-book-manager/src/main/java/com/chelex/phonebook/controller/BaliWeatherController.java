package com.chelex.phonebook.controller;

import com.chelex.phonebook.service.BaliWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BaliWeatherController {

    private final BaliWeatherService baliWeatherService;

    @GetMapping("/weather")
    public ResponseEntity<Void> getBaliWeather() {
        baliWeatherService.logWeather();
        return ResponseEntity.noContent().build();
    }
}
