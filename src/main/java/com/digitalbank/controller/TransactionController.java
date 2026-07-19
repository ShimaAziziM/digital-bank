package com.digitalbank.controller;

import com.digitalbank.dto.TransactionResponseDTO;
import com.digitalbank.entity.Transaction;
import com.digitalbank.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransactionById(@PathVariable int id){
        return  transactionService.getTransactionById(id);
    }

}
