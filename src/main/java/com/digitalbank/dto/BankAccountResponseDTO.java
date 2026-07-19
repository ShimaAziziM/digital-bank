package com.digitalbank.dto;

import com.digitalbank.entity.AccountType;
import com.digitalbank.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankAccountResponseDTO {
    private int id;
    private int customerId;
    private String accountNumber;
    private String accountIban;
    private BigDecimal balance;
    private AccountType accountType;
    private Status status;
}
