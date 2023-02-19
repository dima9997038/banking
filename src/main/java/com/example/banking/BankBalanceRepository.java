package com.example.banking;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BankBalanceRepository {

    private final Map<Long, BigDecimal> storage = new HashMap<>(Map.of(1L, BigDecimal.valueOf(10), 2L, BigDecimal.ONE));

    public BigDecimal getBalance(long accountId) {
        return storage.get(accountId);
    }

    public BigDecimal save(long to, BigDecimal amount) {
        BigDecimal currentAmount = storage.get(to);
        if (currentAmount == null) {
            storage.put(to, amount);
            return amount;
        } else {
            BigDecimal newBalance = currentAmount.add(amount);
            storage.put(to, newBalance);
            return newBalance;
        }
//        return null;
    }

}