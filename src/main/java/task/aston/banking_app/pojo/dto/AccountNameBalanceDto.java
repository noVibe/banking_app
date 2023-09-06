package task.aston.banking_app.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountNameBalanceDto {
    private final String name;
    private final long balance;
}
