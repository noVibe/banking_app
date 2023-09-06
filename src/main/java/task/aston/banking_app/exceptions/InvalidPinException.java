package task.aston.banking_app.exceptions;

public class InvalidPinException extends RuntimeException {
    public InvalidPinException() {
        super("Invalid pin");
    }
}
