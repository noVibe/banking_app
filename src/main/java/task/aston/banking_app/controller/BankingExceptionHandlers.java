package task.aston.banking_app.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import task.aston.banking_app.exceptions.abstract_hierarchy.BankingException;

import java.util.stream.Collectors;

@ControllerAdvice
public class BankingExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<?> handleBankingExceptions(BankingException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleFailedValidation(MethodArgumentNotValidException e) {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .collect(Collectors.joining("\n")));
    }
}
