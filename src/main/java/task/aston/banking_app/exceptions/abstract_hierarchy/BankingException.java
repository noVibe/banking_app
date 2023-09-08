package task.aston.banking_app.exceptions.abstract_hierarchy;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class BankingException extends ResponseStatusException {
    public BankingException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);

    }
}
