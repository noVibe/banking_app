package task.aston.banking_app.exceptions;

import task.aston.banking_app.exceptions.abstract_hierarchy.BankingConflictException;

public class UnexpectedIdMatchingException extends BankingConflictException {
    public UnexpectedIdMatchingException(String message) {
        super(message);
    }
}
