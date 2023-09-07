package task.aston.banking_app.exceptions;

public class NotEnoughFundsException extends BankingBadRequestException {
    public NotEnoughFundsException(long balance, long currencyAmount) {
        super("Current balance is: " + balance + ". Can't withdraw " + currencyAmount);
    }
}
