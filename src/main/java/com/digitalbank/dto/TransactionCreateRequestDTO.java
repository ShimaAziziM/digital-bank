package com.digitalbank.dto;

import com.digitalbank.entity.BankAccount;
import com.digitalbank.entity.Type;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionCreateRequestDTO {
    @NotNull
    private int bankAccountId;

    @DecimalMin(value = "0.01")   // The min value should be 0.01
    private BigDecimal amount;

    @NotNull
    private Type type;

    private String referenceId;


}
