package com.digitalbank.mapper;

import com.digitalbank.dto.TransactionCreateRequestDTO;
import com.digitalbank.dto.TransactionResponseDTO;
import com.digitalbank.entity.BankAccount;
import com.digitalbank.entity.Transaction;

public class TransactionMapper {
    public Transaction toEntity(TransactionCreateRequestDTO dto, BankAccount bankAccount){
        Transaction transaction = new Transaction();

        transaction.setBankAccount(bankAccount);
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setReferenceId(dto.getReferenceId());
        return transaction;
    }
    public TransactionResponseDTO toDTO(Transaction transaction){
        TransactionResponseDTO dto = new TransactionResponseDTO();

        dto.setId(transaction.getId());
        dto.setBankAccountId(transaction.getBankAccount().getId());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType());
        dto.setCreatedAt(transaction.getCreatedAt());
        return dto;

    }
}

// createDTO -> Entity
// Entity -> responseDTO
