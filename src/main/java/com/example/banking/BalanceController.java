package com.example.banking;

import com.example.banking.model.TransferBalance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

//@Component
//@Controller
@Slf4j
@RestController("/balance")
//@AllArgsConstructor
public class BalanceController {

//    @Autowired
    private BankService bankService;

    public BalanceController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable long accountId){
       return   bankService.getBalance(accountId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney (@RequestBody TransferBalance transferBalance){

        return bankService.addMoney(transferBalance.getTo(),transferBalance.getAmount());
    }

    @PostMapping("/transfer")
    public void transfer (@RequestBody TransferBalance transferBalance){
        bankService.makeTransfer(transferBalance);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return "Some message of error";
    }

}
