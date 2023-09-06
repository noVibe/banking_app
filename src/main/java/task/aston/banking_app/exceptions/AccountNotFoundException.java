package task.aston.banking_app.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(long id) {
        super("Nothing found with id:" + id);
    }
}
