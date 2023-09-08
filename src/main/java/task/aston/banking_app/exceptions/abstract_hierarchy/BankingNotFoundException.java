package task.aston.banking_app.exceptions.abstract_hierarchy;

import org.springframework.http.HttpStatus;

public class BankingNotFoundException extends BankingException {
    public BankingNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
