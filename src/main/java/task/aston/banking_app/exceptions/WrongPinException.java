package task.aston.banking_app.exceptions;

import task.aston.banking_app.exceptions.abstract_hierarchy.BankingUnauthorizedException;

public class WrongPinException extends BankingUnauthorizedException {
    public WrongPinException() {
        super("Wrong pin");
    }
}
