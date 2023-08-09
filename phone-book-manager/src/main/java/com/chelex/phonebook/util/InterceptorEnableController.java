package com.chelex.phonebook.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptors")
public class InterceptorEnableController {

    private static boolean enabled = false;

    @GetMapping
    public ResponseEntity<Void> showCustomLogs(@RequestParam boolean enable) {
        enabled = enable;
        return ResponseEntity.noContent().build();
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
