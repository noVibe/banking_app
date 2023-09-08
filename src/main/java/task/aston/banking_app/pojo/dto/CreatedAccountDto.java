package task.aston.banking_app.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatedAccountDto {
    private String name;
    private long id;
}
