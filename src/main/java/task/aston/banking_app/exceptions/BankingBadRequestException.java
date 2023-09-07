package task.aston.banking_app.exceptions;

public abstract class BankingBadRequestException extends BankingException{
    public BankingBadRequestException(String message) {
        super(message);
    }
}
