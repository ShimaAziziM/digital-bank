package com.digitalbank.mapper;

import com.digitalbank.dto.BankAccountCreateRequestDTO;
import com.digitalbank.dto.BankAccountResponseDTO;
import com.digitalbank.entity.BankAccount;
import com.digitalbank.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccountMapper(){

    }
    public BankAccount toEntity(BankAccountCreateRequestDTO dto, Customer customer){
        BankAccount bankAccount = new BankAccount();


        bankAccount.setCustomer(customer);
        //com.digitalbank.entity.BankAccount ENTITY needs full com.digitalbank.entity.Customer object
        bankAccount.setBalance(dto.getBalance());
        bankAccount.setAccountType(dto.getAccountType());
        return bankAccount;
    }
    public BankAccountResponseDTO toDTO(BankAccount bankAccount){
        BankAccountResponseDTO dto = new BankAccountResponseDTO();
        dto.setId(bankAccount.getId());
        dto.setCustomerId(bankAccount.getCustomer().getId());

        dto.setAccountNumber(bankAccount.getAccountNumber());
        dto.setAccountIban(bankAccount.getAccountIban());
        dto.setBalance(bankAccount.getBalance());
        dto.setAccountType(bankAccount.getAccountType());
        dto.setStatus(bankAccount.getStatus());
        return dto;
    }
}


// CreateRequestDTO -> com.digitalbank.entity.BankAccount
// com.digitalbank.entity.BankAccount -> ResponseDTO
