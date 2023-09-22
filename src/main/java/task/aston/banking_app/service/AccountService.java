package task.aston.banking_app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.aston.banking_app.exceptions.AccountNotFoundException;
import task.aston.banking_app.exceptions.NotEnoughFundsException;
import task.aston.banking_app.exceptions.UnexpectedIdMatchingException;
import task.aston.banking_app.mapper.AccountMapper;
import task.aston.banking_app.pojo.dto.account.AccountIdNameBalanceDto;
import task.aston.banking_app.pojo.dto.account.AccountsPageDto;
import task.aston.banking_app.pojo.dto.account.CreatedAccountDto;
import task.aston.banking_app.pojo.dto.account.NewAccountDto;
import task.aston.banking_app.pojo.dto.request.DepositRequest;
import task.aston.banking_app.pojo.dto.request.TransferRequest;
import task.aston.banking_app.pojo.dto.request.WithdrawRequest;
import task.aston.banking_app.pojo.entity.Account;
import task.aston.banking_app.repository.AccountRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final SecurityService securityService;
    private final TransactionLogService transactionLogService;

    @Transactional
    public CreatedAccountDto createAccount(NewAccountDto newAccountDto) {
        String pin = newAccountDto.getPin();
        newAccountDto.setPin(securityService.encodePin(pin));
        Account account = accountMapper.toAccount(newAccountDto);
        accountRepository.save(account);
        return accountMapper.createdFromAccount(account);
    }

    @Transactional(readOnly = true)
    public AccountsPageDto getAccounts(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        long totalAmount = accountRepository.count();
        List<AccountIdNameBalanceDto> list = accountRepository.getPageOfIdNameBalanceDto(page);
        return new AccountsPageDto(totalAmount, list);
    }

    @Transactional
    public AccountIdNameBalanceDto deposit(DepositRequest depositRequest) {
        long toAccountId = depositRequest.getToAccountId();
        long amount = depositRequest.getCurrencyAmount();
        Account account = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(depositRequest.getToAccountId()));
        account.deposit(amount);
        accountRepository.save(account);
        transactionLogService.logDeposit(toAccountId, amount);
        return accountMapper.nameBalanceFromAccount(account);
    }

    @Transactional
    public AccountIdNameBalanceDto withdraw(WithdrawRequest withdrawRequest) {
        Account account = accountRepository.findById(withdrawRequest.getFromAccountId())
                .orElseThrow(() -> new AccountNotFoundException(withdrawRequest.getFromAccountId()));
        securityService.verifyPin(withdrawRequest.getPin(), account.getPin());
        long amount = withdrawRequest.getCurrencyAmount();
        long balance = account.getBalance();
        if (balance < amount) {
            throw new NotEnoughFundsException(balance, amount);
        }
        account.withdraw(amount);
        accountRepository.save(account);
        transactionLogService.logWithdraw(account.getId(), amount);
        return accountMapper.nameBalanceFromAccount(account);
    }

    @Transactional
    public void transfer(TransferRequest transferRequest) {
        if (transferRequest.getFromAccountId() == transferRequest.getToAccountId()) {
            throw new UnexpectedIdMatchingException("Source and goal accounts can't match");
        }
        withdraw(accountMapper.fromTransferToWithdraw(transferRequest));
        deposit(accountMapper.fromTransferToDeposit(transferRequest));
    }
}
