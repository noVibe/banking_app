package task.aston.banking_app.exceptions;

import task.aston.banking_app.exceptions.abstract_hierarchy.BankingNotFoundException;

public class AccountNotFoundException extends BankingNotFoundException {
    public AccountNotFoundException(long id) {
        super("Nothing found with id: " + id);
    }
}
