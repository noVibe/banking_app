package task.aston.banking_app.exceptions;

public class UnexpectedIdMatchingException extends BankingConflictException {
    public UnexpectedIdMatchingException(String message) {
        super(message);
    }
}
