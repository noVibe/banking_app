package task.aston.banking_app.mapper;

import org.mapstruct.MappingConstants;
import task.aston.banking_app.pojo.dto.*;
import task.aston.banking_app.pojo.entity.Account;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {
    Account toAccount(NewAccountDto newAccountDto);
    AccountNameBalanceDto nameBalanceFromAccount(Account account);
    CreatedAccountDto createdFromAccount(Account account);
    WithdrawRequest fromTransferToWithdraw(TransferRequest transferRequest);
    DepositRequest fromTransferToDeposit(TransferRequest depositRequest);
}
