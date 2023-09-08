package task.aston.banking_app.exceptions;

import task.aston.banking_app.exceptions.abstract_hierarchy.BankingConflictException;

public class NameTakenException extends BankingConflictException {
    public NameTakenException(String name) {
        super("This name is already taken: " + name);
    }
}
