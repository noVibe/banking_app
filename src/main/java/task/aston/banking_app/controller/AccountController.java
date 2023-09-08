package task.aston.banking_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.aston.banking_app.pojo.dto.*;
import task.aston.banking_app.service.AccountService;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
@Tag(name = "Account", description = "The Account API")
public class AccountController {
    private AccountService accountService;

    @Operation(summary = "Create new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "409", description = "Name is already taken"),
            @ApiResponse(responseCode = "400", description = "Invalid pin / input")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreatedAccountDto createAccount(@Valid @RequestBody NewAccountDto newAccountDto) {
        return accountService.createAccount(newAccountDto);
    }

    @Operation(summary = "Get page of account name and balance, total account amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page was sent successfully",
                    content = {@Content(schema = @Schema(implementation = AccountsPageDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(schema = @Schema(nullable = true, implementation = String.class),
                            mediaType = MediaType.TEXT_PLAIN_VALUE)})
    })
    @GetMapping
    public AccountsPageDto accountsPage(@RequestParam(name = "pageNumber") int pageNumber,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return accountService.getAccounts(pageNumber, pageSize);
    }

    @Operation(summary = "Withdraw from account by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Withdrawn successfully",
                    content = {@Content(schema = @Schema(implementation = AccountNameBalanceDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(schema = @Schema(nullable = true, implementation = String.class),
                            mediaType = MediaType.TEXT_PLAIN_VALUE)})
    })
    @PostMapping(path = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountNameBalanceDto transfer(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        return accountService.withdraw(withdrawRequest);
    }

    @Operation(summary = "Deposit to account by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposited successfully",
                    content = {@Content(schema = @Schema(implementation = AccountNameBalanceDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(schema = @Schema(nullable = true, implementation = String.class),
                            mediaType = MediaType.TEXT_PLAIN_VALUE)})
    })
    @PostMapping(path = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountNameBalanceDto transfer(@Valid @RequestBody DepositRequest depositRequest) {
        return accountService.deposit(depositRequest);
    }

    @Operation(summary = "Transfer between two accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferred successfully"),
            @ApiResponse(responseCode = "409", description = "Source and goal id are equal",
                    content = {@Content(schema = @Schema(nullable = true, implementation = String.class),
                            mediaType = MediaType.TEXT_PLAIN_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(schema = @Schema(nullable = true, implementation = String.class),
                            mediaType = MediaType.TEXT_PLAIN_VALUE)})
    })
    @PostMapping(path = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void transfer(@Valid @RequestBody TransferRequest transferRequest) {
        accountService.transfer(transferRequest);
    }


}
