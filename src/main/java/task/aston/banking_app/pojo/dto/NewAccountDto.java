package task.aston.banking_app.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(description = "Used to create accounts")
@Data
@AllArgsConstructor
public class NewAccountDto {

    @Schema(description = "Must be 4 digits", example = "1234")
    @NotBlank(message = "Must provide a pin")
    private String pin;

    @Schema(description = "Must be unique", minLength = 3, maxLength = 50, example = "my_account")
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
}
