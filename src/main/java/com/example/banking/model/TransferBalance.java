package com.example.banking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferBalance {

    private long from;
    private long to;
    private BigDecimal amount;



}
