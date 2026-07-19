package com.digitalbank.controller;

import com.digitalbank.dto.BankAccountCreateRequestDTO;
import com.digitalbank.dto.BankAccountResponseDTO;
import com.digitalbank.dto.BankAccountUpdateRequestDTO;
import com.digitalbank.entity.BankAccount;
import com.digitalbank.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BankAccount")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @PostMapping("/createBankAccount")
    public BankAccount createBankAccount(@RequestBody  BankAccountCreateRequestDTO dto){
        return bankAccountService.createBankAccount(dto);
    }
    @PostMapping("/updateBankAccount")
    public BankAccount updateBankAccount(@RequestBody BankAccountUpdateRequestDTO dto){
        return bankAccountService.updateBankAccount(dto);
    }
    @GetMapping("/getByAccountId/{id}")
    public BankAccountResponseDTO getBankAccountById(@PathVariable int id){
        return bankAccountService.findByAccountId(id);
    }

}
