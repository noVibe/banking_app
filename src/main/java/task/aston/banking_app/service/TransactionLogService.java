package task.aston.banking_app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.aston.banking_app.exceptions.AccountNotFoundException;
import task.aston.banking_app.pojo.dto.transaction_log.TransactionLogDto;
import task.aston.banking_app.pojo.dto.transaction_log.TransactionPageDto;
import task.aston.banking_app.pojo.entity.TransactionLog;
import task.aston.banking_app.repository.AccountRepository;
import task.aston.banking_app.repository.TransactionLogRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionLogService {
    private final TransactionLogRepository transactionLogRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void logWithdraw(long accountId, long amount) {
        TransactionLog withdraw = TransactionLog.newBalanceWithdrawLog(accountId, amount);
        transactionLogRepository.save(withdraw);
    }
    @Transactional
    public void logDeposit(long accountId, long amount) {
        TransactionLog deposit = TransactionLog.newBalanceDepositLog(accountId, amount);
        transactionLogRepository.save(deposit);
    }

    @Transactional
    public TransactionPageDto getAccountTransactions(long accountId, int pageNumber, int pageSize) {
        if (!accountRepository.existsById(accountId)) {
            throw new AccountNotFoundException(accountId);
        }
        long totalAmount = transactionLogRepository.countAllByAccountId(accountId);
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<TransactionLogDto> list = transactionLogRepository.getPageOfTransactionLogDto(page, accountId);
        return new TransactionPageDto(totalAmount, list);
    }
}
