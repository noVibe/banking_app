package task.aston.banking_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import task.aston.banking_app.pojo.dto.transaction_log.TransactionPageDto;
import task.aston.banking_app.service.TransactionLogService;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
    TransactionLogService transactionLogService;

    @Operation(summary = "Get page of account transactions, total account amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page was sent successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public TransactionPageDto getAccountTransactions(@PathVariable(name = "id") long id,
                                                     @RequestParam(name = "pageNumber") int pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return transactionLogService.getAccountTransactions(id, pageNumber, pageSize);
    }
}
