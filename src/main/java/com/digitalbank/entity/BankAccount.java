package com.digitalbank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)  // customer must exist
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;


    @Column(unique = true, nullable = false, length = 16)
    private String accountNumber;


    @Column(unique = true, nullable = false, length = 26)
    private String accountIban;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private BigDecimal balance;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public BankAccount() {}
    public int getId(){
        return id;
    }

    public void setCustomerId(Customer customer){
        this.customer = customer;
    }

    
    public String getAccountNumber(){
        return accountNumber;
    }

    public String getAccountIban(){
        return accountIban;
    }
    public Customer getCustomer(){
        return customer;
    }
    public AccountType getAccountType(){
        return accountType;
    }
    public BigDecimal getBalance(){
        return balance;
    }
    public Status getStatus(){
        return status;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setAccountIban(String accountIban) {
        this.accountIban = accountIban;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

