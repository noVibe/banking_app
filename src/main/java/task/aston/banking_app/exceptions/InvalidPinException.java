package task.aston.banking_app.exceptions;

public class InvalidPinException extends BankingBadRequestException {
    public InvalidPinException() {
        super("Invalid pin");
    }
}
