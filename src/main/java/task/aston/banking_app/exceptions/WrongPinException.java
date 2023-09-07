package task.aston.banking_app.exceptions;

public class WrongPinException extends BankingBadRequestException{
    public WrongPinException() {
        super("Wrong pin");
    }
}
