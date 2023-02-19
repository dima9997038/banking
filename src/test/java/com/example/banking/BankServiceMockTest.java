package com.example.banking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceMockTest {
    @Mock
    private BankBalanceRepository repository;
    @Test
    public void getBalance(){
        when(repository.getBalance(1L)).thenReturn(BigDecimal.ONE);
        BankService bankService=new BankService(repository);
        assertEquals(BigDecimal.valueOf(1L),bankService.getBalance(1L));
    }

}
