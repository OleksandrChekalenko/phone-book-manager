package com.chelex.phonebook.controller.handlers;

import com.chelex.phonebook.util.exception.Error;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.chelex.phonebook.controller")
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        Error error = Error.builder()
                .errorDetails(new Error.ErrorDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage()))
                .build();
        return ResponseEntity.status(error.getErrorDetails().getStatus()).body(error);
    }
}
