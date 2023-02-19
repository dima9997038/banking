package com.example.banking;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class BankServiceTest {


    private BankBalanceRepository repository = new BankBalanceRepository();
    private BankService bankService = new BankService(repository);


    @Test
    void getBalance() {
        BigDecimal balance = bankService.getBalance(1L);
        assertEquals(balance,BigDecimal.valueOf(10L));
    }

    @Test
    void addMoney() {
        bankService.addMoney(1L, BigDecimal.ONE);
        BigDecimal balance = bankService.getBalance(1L);
        assertEquals(balance,BigDecimal.valueOf(11L));

    }
}