package task.aston.banking_app.exceptions.abstract_hierarchy;

import org.springframework.http.HttpStatus;

public abstract class BankingBadRequestException extends BankingException {

    public BankingBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

   }
