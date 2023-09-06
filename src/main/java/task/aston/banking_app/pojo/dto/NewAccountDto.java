package task.aston.banking_app.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewAccountDto {
    @NotBlank(message = "Must provide a pin")
    private String pin;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
}
