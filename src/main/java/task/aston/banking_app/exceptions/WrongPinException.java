package task.aston.banking_app.exceptions;

public class WrongPinException extends RuntimeException{
    public WrongPinException() {
        super("Wrong pin");
    }
}
