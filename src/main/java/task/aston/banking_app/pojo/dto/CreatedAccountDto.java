package task.aston.banking_app.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(description = "Contains name and id of created account. Sent by server")
@Data
@AllArgsConstructor
public class CreatedAccountDto {
    private final String name;
    private final long id;
}
