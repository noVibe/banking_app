package task.aston.banking_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import task.aston.banking_app.exceptions.AccountNotFoundException;
import task.aston.banking_app.exceptions.BankingBadRequestException;
import task.aston.banking_app.exceptions.BankingConflictException;

import java.util.stream.Collectors;

@ControllerAdvice
public class BankingExceptionHandlers {
    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBankingConflicts(BankingConflictException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBankingBadRequests(BankingBadRequestException e) {
        return ResponseEntity
                .badRequest()
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
