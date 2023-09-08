package task.aston.banking_app.exceptions.abstract_hierarchy;

import org.springframework.http.HttpStatus;

public abstract class BankingException extends RuntimeException{
    public BankingException(String message) {
        super(message);
    }
    public abstract HttpStatus getHttpStatus();
}
