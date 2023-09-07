package task.aston.banking_app.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(description = "Contains actual name and balance of account. Sent by server")
@Data
@AllArgsConstructor
public class AccountNameBalanceDto {
    private final String name;
    private final long balance;
}
