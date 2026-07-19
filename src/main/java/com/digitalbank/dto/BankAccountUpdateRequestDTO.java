package com.digitalbank.dto;

import com.digitalbank.entity.AccountType;
import com.digitalbank.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountUpdateRequestDTO {
    private int id;
    private Status status;
    private AccountType accountType;
}
