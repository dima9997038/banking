package com.example.banking;

import com.example.banking.model.TransferBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankService {
    final
    BankBalanceRepository repository;

    public BankService(BankBalanceRepository repository) {
        this.repository = repository;
    }

    public BigDecimal getBalance(long accountId) {
        BigDecimal balance= repository.getBalance(accountId);
        if(balance==null){
            throw new IllegalArgumentException();
        }
        return balance;
    }

    public BigDecimal addMoney(long to, BigDecimal amount) {
        return repository.save(to,amount);
    }

    public void makeTransfer(TransferBalance transferBalance) {
        BigDecimal balanceFrom = getBalance(transferBalance.getFrom());
        BigDecimal balanceTo = getBalance(transferBalance.getTo());
        if (balanceTo==null||balanceFrom==null) {throw new IllegalArgumentException("no id");}
        if(transferBalance.getAmount().compareTo(balanceFrom)>0){ throw new IllegalArgumentException("No many");}
        BigDecimal newBalanceFrom = balanceFrom.subtract(transferBalance.getAmount());
        BigDecimal newBalanceTo = balanceTo.add(transferBalance.getAmount());
        repository.save(transferBalance.getFrom(),newBalanceFrom);
        repository.save(transferBalance.getTo(),newBalanceTo);


    }
}
