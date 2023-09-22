package task.aston.banking_app.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import task.aston.banking_app.pojo.dto.transaction_log.TransactionLogDto;
import task.aston.banking_app.pojo.entity.TransactionLog;

import java.util.List;

@Repository
public interface TransactionLogRepository extends CrudRepository<TransactionLog, Long>, PagingAndSortingRepository<TransactionLog, Long> {
    long countAllByAccountId(long accountId);

    @Query("select new task.aston.banking_app.pojo.dto.transaction_log.TransactionLogDto(id, operation, amount, dateTime) " +
            "from TransactionLog t where t.accountId = :accountId")
    List<TransactionLogDto> getPageOfTransactionLogDto(Pageable page, long accountId);
}
