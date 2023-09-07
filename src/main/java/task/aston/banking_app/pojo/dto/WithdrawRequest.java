package task.aston.banking_app.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(description = "Used to withdraw currency")
@Data
@AllArgsConstructor
public final class WithdrawRequest {

    @Schema(description = "Must be 4 digits", example = "1234")
    @NotBlank(message = "Pin required")
    private final String pin;

    @Schema(description = "Account id. Must be positive", example = "1")
    @Positive
    private final long fromAccountId;

    @Schema(description = "Must be positive. Can't exceed the current balance of account", example = "50")
    @Positive
    private final long currencyAmount;
}
