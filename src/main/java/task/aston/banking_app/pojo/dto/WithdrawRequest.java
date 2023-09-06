package task.aston.banking_app.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class WithdrawRequest {

    @NotBlank(message = "Pin required")
    private final String pin;

    @Positive
    private final long fromAccountId;

    @Positive
    private final long currencyAmount;
}
