package task.aston.banking_app.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import task.aston.banking_app.mapper.Mapper;
import task.aston.banking_app.pojo.dto.AccountNameBalanceDto;
import task.aston.banking_app.pojo.dto.AccountsPageDto;
import task.aston.banking_app.pojo.dto.NewAccountDto;
import task.aston.banking_app.pojo.entity.Account;
import task.aston.banking_app.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import static data_preparation.PreparedData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    void createAccount() {
        NewAccountDto dto = new NewAccountDto("5555", "name");
        Account account = new Account();
        account.setId(1);
        account.setPin(dto.getPin());
        account.setName(dto.getName());
        long expected = account.getId();
        when(accountRepository.existsByName(anyString()))
                .thenReturn(false);
        when(securityService.validatePinFormat(anyString()))
                .thenReturn(account.getPin());
        when(mapper.toAccount(dto))
                .thenReturn(account);
        when(accountRepository.save(account))
                .thenReturn(account);
        assertEquals(out.createAccount(dto), expected);
    }

    @Test
    void getAccounts() {
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
    void deposit() {
        AccountNameBalanceDto result =
                new AccountNameBalanceDto(ACCOUNT.getName(),
                        ACCOUNT.getBalance() + DEPOSIT_REQUEST.getCurrencyAmount());
        when(accountRepository.findById(ACCOUNT.getId()))
                .thenReturn(Optional.of(ACCOUNT));
        when(accountRepository.save(ACCOUNT))
                .thenReturn(ACCOUNT);
        when(mapper.fromAccount(ACCOUNT))
                .thenReturn(result);

        assertEquals(out.withdraw(WITHDRAW_REQUEST), result);
    }

    @Test
    void withdraw() {
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

}