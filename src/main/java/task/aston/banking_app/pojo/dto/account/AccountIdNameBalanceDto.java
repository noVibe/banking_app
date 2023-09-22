package task.aston.banking_app.pojo.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Contains actual id, beneficial name and balance of account. Sent by server")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountIdNameBalanceDto {
    private long id;
    private String name;
    private long balance;
}
