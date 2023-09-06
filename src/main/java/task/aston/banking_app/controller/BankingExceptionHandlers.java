package task.aston.banking_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import task.aston.banking_app.exceptions.*;

@ControllerAdvice
public class BankingExceptionHandlers {
    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidPinException(InvalidPinException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNameTakenException(NameTakenException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNotEnoughFundsException(NotEnoughFundsException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleWrongPinException(WrongPinException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<?> handleWrongPinException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .toArray());
    }
}
