package task.aston.banking_app.pojo.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class DepositRequest {
    @Positive
    private final long toAccountId;

    @Positive
    private final long currencyAmount;
}
