package com.example.banking;

import com.example.banking.model.TransferBalance;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Objects;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private BankBalanceRepository repository;

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(10));
    }

    @Test
    void transfer() {
    }

    @Test
    void addMoney() throws Exception {
//        when(repository.save(2L,BigDecimal.ONE)).thenReturn(BigDecimal.TEN);
//        BankService bankService=new BankService(repository);

        String json = new ObjectMapper().writeValueAsString(new TransferBalance(1L, 2L, BigDecimal.ONE));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/add")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
}