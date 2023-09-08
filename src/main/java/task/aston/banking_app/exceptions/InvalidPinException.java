package task.aston.banking_app.exceptions;

import task.aston.banking_app.exceptions.abstract_hierarchy.BankingBadRequestException;

public class InvalidPinException extends BankingBadRequestException {
    public InvalidPinException() {
        super("Invalid pin");
    }
}
