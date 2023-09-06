package task.aston.banking_app.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class
TransferRequest {
    @Positive
    private final long fromAccountId;
    @Positive
    private final long toAccountId;
    @NotBlank(message = "Pin required")
    private String pin;
    @Positive
    private final long currencyAmount;
}
