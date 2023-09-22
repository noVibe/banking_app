package task.aston.banking_app.pojo.dto.transaction_log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.aston.banking_app.pojo.enums.Operation;

import java.time.LocalDateTime;

@Schema(description = "Contains id, operation type, amount and operation of transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionLogDto {
    private long id;

    private Operation operation;

    private long amount;

    private LocalDateTime dateTime;
}
