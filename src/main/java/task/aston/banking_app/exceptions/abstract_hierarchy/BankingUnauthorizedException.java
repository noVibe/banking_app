package task.aston.banking_app.exceptions.abstract_hierarchy;

import org.springframework.http.HttpStatus;

public abstract class BankingUnauthorizedException extends BankingException {
    public BankingUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

}
