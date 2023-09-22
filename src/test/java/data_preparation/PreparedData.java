package data_preparation;

import com.github.javafaker.Faker;
import task.aston.banking_app.pojo.dto.account.AccountIdNameBalanceDto;
import task.aston.banking_app.pojo.dto.account.CreatedAccountDto;
import task.aston.banking_app.pojo.dto.account.NewAccountDto;
import task.aston.banking_app.pojo.dto.request.DepositRequest;
import task.aston.banking_app.pojo.dto.request.WithdrawRequest;
import task.aston.banking_app.pojo.entity.Account;
import task.aston.banking_app.pojo.entity.TransactionLog;

import java.util.List;
import java.util.stream.IntStream;

public class PreparedData {
    private static final Faker faker = new Faker();

    public static final List<AccountIdNameBalanceDto> NAME_BALANCE_DTOS = getNameBalanceDtoList();

    public static final Account ACCOUNT = prepareAccount();
    public static final WithdrawRequest WITHDRAW_REQUEST = prepareWithdrawRequest();
    public static final DepositRequest DEPOSIT_REQUEST = prepareDepositRequest();
    public static final NewAccountDto NEW_ACCOUNT_DTO = new NewAccountDto("5555", "name");
    public static final CreatedAccountDto CREATED_ACCOUNT_DTO = new CreatedAccountDto("name", 1);
    public static final TransactionLog WITHDRAW_TRANSACTION_LOG = prepareWithdrawLog();
    public static final TransactionLog DEPOSIT_TRANSACTION_LOG = prepareDepositLog();


    public static List<AccountIdNameBalanceDto> getPageOfNameBalanceDto(int pageNumber, int pageSize) {
        return NAME_BALANCE_DTOS.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }
    private static List<AccountIdNameBalanceDto> getNameBalanceDtoList() {
        return IntStream.range(1,51)
                .mapToObj(id -> new AccountIdNameBalanceDto(id,
                        faker.name().fullName(),
                        faker.random().nextInt(1000, 10000)))
                .toList();
    }
    private static Account prepareAccount() {
        Account account = new Account();
        account.setId(0);
        account.setPin("encoded");
        account.setName(NAME_BALANCE_DTOS.get(0).getName());
        account.setBalance(NAME_BALANCE_DTOS.get(0).getBalance());
        return account;
    }

    private static WithdrawRequest prepareWithdrawRequest() {
        return new WithdrawRequest(ACCOUNT.getPin(), ACCOUNT.getId(), 100);
    }
    private static DepositRequest prepareDepositRequest() {
        return new DepositRequest(ACCOUNT.getId(), 100);
    }

    private static TransactionLog prepareWithdrawLog() {
        return TransactionLog.newBalanceWithdrawLog(1, 100);
    }
    private static TransactionLog prepareDepositLog() {
        return TransactionLog.newBalanceDepositLog(1, 100);
    }

}
