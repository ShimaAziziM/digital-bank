package com.digitalbank.dto;

import com.digitalbank.entity.Type;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponseDTO {
    private int id;
    private int bankAccountId;
    private BigDecimal amount;
    private Type type;
    private LocalDateTime createdAt;
    private String referenceId;

}
