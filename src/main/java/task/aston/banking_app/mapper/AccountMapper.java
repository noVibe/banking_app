package task.aston.banking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import task.aston.banking_app.pojo.dto.account.AccountIdNameBalanceDto;
import task.aston.banking_app.pojo.dto.account.CreatedAccountDto;
import task.aston.banking_app.pojo.dto.account.NewAccountDto;
import task.aston.banking_app.pojo.dto.request.DepositRequest;
import task.aston.banking_app.pojo.dto.request.TransferRequest;
import task.aston.banking_app.pojo.dto.request.WithdrawRequest;
import task.aston.banking_app.pojo.entity.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    Account toAccount(NewAccountDto newAccountDto);
    AccountIdNameBalanceDto nameBalanceFromAccount(Account account);
    CreatedAccountDto createdFromAccount(Account account);
    WithdrawRequest fromTransferToWithdraw(TransferRequest transferRequest);
    DepositRequest fromTransferToDeposit(TransferRequest depositRequest);
}
