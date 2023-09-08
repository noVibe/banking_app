package task.aston.banking_app.exceptions.abstract_hierarchy;

import org.springframework.http.HttpStatus;

public abstract class BankingConflictException extends BankingException {
    public BankingConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
