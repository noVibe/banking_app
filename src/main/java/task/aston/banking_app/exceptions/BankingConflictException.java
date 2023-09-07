package task.aston.banking_app.exceptions;

public abstract class BankingConflictException extends BankingException{
    public BankingConflictException(String message) {
        super(message);
    }
}
