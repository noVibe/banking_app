package task.aston.banking_app.exceptions;

public class NameTakenException extends RuntimeException{
    public NameTakenException(String name) {
        super("This name is already taken: " + name);
    }
}
