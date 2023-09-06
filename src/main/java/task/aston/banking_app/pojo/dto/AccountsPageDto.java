package task.aston.banking_app.pojo.dto;

import java.util.List;

public record AccountsPageDto(long totalCount, List<AccountNameBalanceDto> accounts) {
}
