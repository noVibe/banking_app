package task.aston.banking_app.exceptions;

public class NameTakenException extends BankingConflictException {
    public NameTakenException(String name) {
        super("This name is already taken: " + name);
    }
}
