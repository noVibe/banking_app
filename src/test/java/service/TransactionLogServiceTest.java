package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import task.aston.banking_app.exceptions.AccountNotFoundException;
import task.aston.banking_app.pojo.dto.transaction_log.TransactionPageDto;
import task.aston.banking_app.pojo.entity.TransactionLog;
import task.aston.banking_app.repository.AccountRepository;
import task.aston.banking_app.repository.TransactionLogRepository;
import task.aston.banking_app.service.TransactionLogService;

import static data_preparation.PreparedData.DEPOSIT_TRANSACTION_LOG;
import static data_preparation.PreparedData.WITHDRAW_TRANSACTION_LOG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionLogServiceTest {
    @Mock
    TransactionLogRepository transactionLogRepository;
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    TransactionLogService out;

    @Test
    public void logWithdraw_success() {
        when(transactionLogRepository.save(any(TransactionLog.class)))
                .thenReturn(WITHDRAW_TRANSACTION_LOG);
        out.logWithdraw(1, 100);
        verify(transactionLogRepository, only()).save(WITHDRAW_TRANSACTION_LOG);
    }

    @Test
    public void logDeposit_success() {
        when(transactionLogRepository.save(any(TransactionLog.class)))
                .thenReturn(DEPOSIT_TRANSACTION_LOG);
        out.logDeposit(1, 100);
        verify(transactionLogRepository, only()).save(DEPOSIT_TRANSACTION_LOG);
    }
    @Test
    public void getAccountTransactions_success() {
        TransactionPageDto result = new TransactionPageDto(0, null);
        when(accountRepository.existsById(anyLong()))
                .thenReturn(true);
        when(transactionLogRepository.countAllByAccountId(anyLong()))
                .thenReturn(0L);
        when(transactionLogRepository.getPageOfTransactionLogDto(any(Pageable.class), anyLong()))
                .thenReturn(null);
        assertEquals(out.getAccountTransactions(1,1,1), result);
    }

    @Test
    public void not_found_exception() {
        when(accountRepository.existsById(anyLong()))
                .thenReturn(false);
        assertThrows(AccountNotFoundException.class, () -> out.getAccountTransactions(1,1, 1));
    }
}
