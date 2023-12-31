package task.aston.banking_app.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import task.aston.banking_app.pojo.dto.account.AccountIdNameBalanceDto;
import task.aston.banking_app.pojo.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>, PagingAndSortingRepository<Account, Long> {
    @Query("select new task.aston.banking_app.pojo.dto.account.AccountIdNameBalanceDto(id, name, balance) from Account")
    List<AccountIdNameBalanceDto> getPageOfIdNameBalanceDto(Pageable page);
}
