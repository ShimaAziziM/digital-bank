package com.digitalbank.service;

import com.digitalbank.dto.TransactionCreateRequestDTO;
import com.digitalbank.dto.TransactionResponseDTO;
import com.digitalbank.entity.BankAccount;
import com.digitalbank.entity.Transaction;
import com.digitalbank.exception.BankAccountNotFoundException;
import com.digitalbank.exception.TransactionNotFound;
import com.digitalbank.repository.BankAccountRepository;
import com.digitalbank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    final private TransactionRepository transactionRepository;
    final private BankAccountRepository bankAccountRepository;
    public TransactionService(TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public Transaction createTransaction(TransactionCreateRequestDTO dto){
        Transaction transaction = new Transaction();
        BankAccount bankAccount = bankAccountRepository.findById(dto.getBankAccountId())
                .orElseThrow(()-> new BankAccountNotFoundException("Bank Account Not Found"));
        LocalDateTime createdAt  = LocalDateTime.now();
        transaction.setCreatedAt(createdAt);
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setReferenceId(dto.getReferenceId());
        transaction.setBankAccount(bankAccount);

        return transactionRepository.save(transaction);

    }

    public TransactionResponseDTO getTransactionById(int id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new TransactionNotFound("Transaction Not Found"));

        TransactionResponseDTO dto = new TransactionResponseDTO();

        dto.setId(transaction.getId());
        dto.setBankAccountId(transaction.getBankAccount().getId());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType());
        dto.setCreatedAt(transaction.getCreatedAt());
        dto.setReferenceId(transaction.getReferenceId());
        return dto;

    }

    public TransactionResponseDTO getTransactionsByReferenceId(String referenceId){
        return (TransactionResponseDTO) transactionRepository.findByReferenceId(referenceId);

    }

}
