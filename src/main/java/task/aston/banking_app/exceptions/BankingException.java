package task.aston.banking_app.exceptions;

public abstract class BankingException extends RuntimeException{
    public BankingException(String message) {
        super(message);
    }
}
