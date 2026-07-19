package com.digitalbank.dto;

import com.digitalbank.entity.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class BankAccountCreateRequestDTO {
    @NotNull
    private int customerId;
    @Positive
    private BigDecimal balance;
    @NotNull
    private AccountType accountType;

}
