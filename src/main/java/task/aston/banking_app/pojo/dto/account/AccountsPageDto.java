package task.aston.banking_app.pojo.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Contains total account number and a list of account name and balance")

public record AccountsPageDto(
        @Schema(description = "Total count")
        long totalCount,
        @Schema(description = "Accounts list")
        List<AccountIdNameBalanceDto> accounts) {
}
