package com.digitalbank.service;

import com.digitalbank.dto.BankAccountCreateRequestDTO;
import com.digitalbank.dto.BankAccountResponseDTO;
import com.digitalbank.dto.BankAccountUpdateRequestDTO;
import com.digitalbank.entity.AccountType;
import com.digitalbank.entity.BankAccount;
import com.digitalbank.entity.Customer;
import com.digitalbank.entity.Status;
import com.digitalbank.exception.*;
import com.digitalbank.mapper.BankAccountMapper;
import com.digitalbank.repository.BankAccountRepository;
import com.digitalbank.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankAccountService {
    final private BankAccountRepository bankAccountRepository;
    //اینو  اضافه کردم
    final private CustomerRepository customerRepository;
    final private BankAccountMapper bankAccountMapper;

    private static final Logger log = LoggerFactory.getLogger(BankAccountService.class);


    public BankAccountService(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
        this.bankAccountMapper = bankAccountMapper;
    }
    public BankAccount createBankAccount(BankAccountCreateRequestDTO dto){
        BankAccount bankAccount = new BankAccount();
        //اینو اضافه کردم
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found."));
        String generateAccountNumber = generateAccountNumber();
        String generateIban = generateIban(generateAccountNumber);

        bankAccount.setAccountNumber(generateAccountNumber);
        bankAccount.setAccountIban(generateIban);

        bankAccount.setBalance(dto.getBalance());
        bankAccount.setAccountType(dto.getAccountType());
        bankAccount.setStatus(Status.ACTIVE);
        //اینو اضافه کردم
        bankAccount.setCustomer(customer);

        return  bankAccountRepository.save(bankAccount);
    }
    private String generateAccountNumber() {

        StringBuilder sb = new StringBuilder("8262");

        for (int i = 0; i < 12; i++) {
            int digit = (int) (Math.random() * 10);
            sb.append(digit);
        }

        return sb.toString();
    }
    private String generateIban(String accountNumber){
        StringBuilder sb = new StringBuilder("IR");
        for(int i = 0; i < 6; i++){
            int digit = (int) (Math.random() * 10);
            sb.append(digit);
        }
        sb.append(accountNumber);
        sb.append("01");
        return sb.toString();
    }
    public BankAccount updateBankAccount(BankAccountUpdateRequestDTO dto){
        BankAccount bankAccount = bankAccountRepository.findById(dto.getId())
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account Not Found."));
        if(dto.getStatus() != null) {
            bankAccount.setStatus(dto.getStatus());
        }
        if(dto.getAccountType() != null){
            bankAccount.setAccountType(dto.getAccountType());
        }

        return bankAccountRepository.save(bankAccount);
    }

    public BankAccountResponseDTO findByAccountId(int id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account Not Found."));
        return bankAccountMapper.toDTO(bankAccount);
    }

    public List<BankAccount> findByCustomerId(int customer_id) {
        return bankAccountRepository.findByCustomer_Id(customer_id);
    }

    public AccountType findAccountTypeById(int id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account Not Found."));
        return account.getAccountType();
    }

    public Status findAccountStatusById(int id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account Not Found."));
        return account.getStatus();
    }

    public String getIbanByAccountNumber(String accountNumber) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Account not found"));

        return account.getAccountIban();
    }

    public void deposit(BigDecimal amount, int id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account Not Found."));


        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Invalid Amount.");
        }

        //BigDecimal is object not primitive (+ no)
        account.setBalance(account.getBalance().add(amount));
        bankAccountRepository.save(account);
    }

    public void withdraw(BigDecimal amount, int id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account Not Found."));
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Invalid Amount.");
        }
        if (amount.compareTo(account.getBalance()) > 0) {
            throw new InsufficientBalanceException("Insufficient Balance.");
        }
        if (account.getStatus() == Status.CLOSED) {
            throw new AccountClosedException("Account is CLOSED.");
        }
        account.setBalance(account.getBalance().subtract(amount));
        bankAccountRepository.save(account);
    }

    @Transactional   //rollback
    public void transfer(BigDecimal amount, int fromAccountId, int toAccountId) {
        if (fromAccountId == toAccountId || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.warn("Invalid transfer attempt: fromAccountId={}, toAccountId={}, amount={}", fromAccountId, toAccountId, amount);
            throw new InvalidInputException("Invalid transfer input.");
        }

        withdraw(amount, fromAccountId);
        deposit(amount, toAccountId);

    }


}
