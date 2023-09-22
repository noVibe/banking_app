package task.aston.banking_app.pojo.dto.transaction_log;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Contains total account number and a list of transactions")

public record TransactionPageDto(

        @Schema(description = "Total count")
        long totalCount,
        @Schema(description = "Transaction list")
        List<TransactionLogDto> transactions) {}
