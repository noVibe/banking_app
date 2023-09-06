package task.aston.banking_app.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import task.aston.banking_app.pojo.dto.*;
import task.aston.banking_app.service.AccountService;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    @PostMapping
    public long createAccount(@Valid @RequestBody NewAccountDto newAccountDto) {
        return accountService.createAccount(newAccountDto);
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public AccountsPageDto accountsPage(@RequestParam(name = "pageNumber") int pageNumber,
                                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return accountService.getAccounts(pageNumber, pageSize);
    }

    @PostMapping("/withdraw")
    public AccountNameBalanceDto transfer(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        return accountService.withdraw(withdrawRequest);
    }

    @PostMapping("/deposit")
    public AccountNameBalanceDto transfer(@Valid @RequestBody DepositRequest depositRequest) {
        return accountService.deposit(depositRequest);
    }

    @PostMapping("/transfer")
    public void transfer(@Valid @RequestBody TransferRequest transferRequest) {
        accountService.transfer(transferRequest);
    }


}
