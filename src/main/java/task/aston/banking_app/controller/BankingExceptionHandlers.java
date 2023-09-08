package task.aston.banking_app.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class BankingExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<?> handleFailedValidation(MethodArgumentNotValidException e) {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getFieldErrors()
                        .stream()
                        .collect(Collectors.groupingBy(FieldError::getField,
                                Collectors.mapping(FieldError::getDefaultMessage, Collectors.joining("; ")))));
    }
}
