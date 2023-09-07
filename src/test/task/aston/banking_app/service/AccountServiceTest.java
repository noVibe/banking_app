package task.aston.banking_app.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import task.aston.banking_app.exceptions.AccountNotFoundException;
import task.aston.banking_app.exceptions.NameTakenException;
import task.aston.banking_app.exceptions.NotEnoughFundsException;
import task.aston.banking_app.exceptions.UnexpectedIdMatchingException;
import task.aston.banking_app.mapper.Mapper;
import task.aston.banking_app.pojo.dto.AccountNameBalanceDto;
import task.aston.banking_app.pojo.dto.AccountsPageDto;
import task.aston.banking_app.pojo.dto.TransferRequest;
import task.aston.banking_app.pojo.entity.Account;
import task.aston.banking_app.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import static data_preparation.PreparedData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    Mapper mapper;
    @Mock
    SecurityService securityService;

    @InjectMocks
    AccountService out;

    @Test
    void createAccount_success() {
        Account account = new Account();
        account.setId(1);
        account.setPin(NEW_ACCOUNT_DTO.getPin());
        account.setName(NEW_ACCOUNT_DTO.getName());
        String expected = String.valueOf(account.getId());
        when(accountRepository.existsByName(anyString()))
                .thenReturn(false);
        when(securityService.validatePinFormat(anyString()))
                .thenReturn(account.getPin());
        when(mapper.toAccount(NEW_ACCOUNT_DTO))
                .thenReturn(account);
        when(accountRepository.save(account))
                .thenReturn(account);
        assertEquals(out.createAccount(NEW_ACCOUNT_DTO), expected);
    }

    @Test
    void getAccounts_success() {
        long totalCount = NAME_BALANCE_DTOS.size();
        int pageNumber = 0;
        int pageSize = 10;
        List<AccountNameBalanceDto> list = getPageOfNameBalanceDto(pageNumber, pageSize);
        AccountsPageDto result = new AccountsPageDto(totalCount, list);
        when(accountRepository.count())
                .thenReturn(totalCount);
        when(accountRepository.getPageOfNameBalanceDto(any()))
                .thenReturn(list);
        assertEquals(out.getAccounts(pageNumber, pageSize), result);
    }

    @Test
    void deposit_success() {
        AccountNameBalanceDto result =
                new AccountNameBalanceDto(ACCOUNT.getName(),
                        ACCOUNT.getBalance() + DEPOSIT_REQUEST.getCurrencyAmount());
        when(accountRepository.findById(ACCOUNT.getId()))
                .thenReturn(Optional.of(ACCOUNT));
        when(accountRepository.save(ACCOUNT))
                .thenReturn(ACCOUNT);
        when(mapper.fromAccount(ACCOUNT))
                .thenReturn(result);

        assertEquals(out.deposit(DEPOSIT_REQUEST), result);
    }

    @Test
    void withdraw_success() {
        AccountNameBalanceDto result =
                new AccountNameBalanceDto(ACCOUNT.getName(),
                        ACCOUNT.getBalance() - WITHDRAW_REQUEST.getCurrencyAmount());
        when(accountRepository.findById(ACCOUNT.getId()))
                .thenReturn(Optional.of(ACCOUNT));
        when(accountRepository.save(ACCOUNT))
                .thenReturn(ACCOUNT);
        when(mapper.fromAccount(ACCOUNT))
                .thenReturn(result);

        assertEquals(out.withdraw(WITHDRAW_REQUEST), result);
    }

    @Test
    void not_found_exception() {
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> out.withdraw(WITHDRAW_REQUEST));
        assertThrows(AccountNotFoundException.class, () -> out.deposit(DEPOSIT_REQUEST));
    }

    @Test
    void name_already_taken_exception() {
        when(accountRepository.existsByName(anyString()))
                .thenReturn(true);
        assertThrows(NameTakenException.class, () -> out.createAccount(NEW_ACCOUNT_DTO));
    }

    @Test
    void not_enough_funds_exception() {
        Account poorAccount = new Account();
        poorAccount.setBalance(0);
        when(accountRepository.findById(poorAccount.getId()))
                .thenReturn(Optional.of(poorAccount));
        assertThrows(NotEnoughFundsException.class, () -> out.withdraw(WITHDRAW_REQUEST));
    }

    @Test
    void id_conflict_on_transfer_exception() {
        assertThrows(UnexpectedIdMatchingException.class,
                () -> out.transfer(new TransferRequest(1,1, "1234", 100)));
    }

}