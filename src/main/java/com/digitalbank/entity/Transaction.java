package com.digitalbank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bankAccountId", nullable = false)
    private BankAccount bankAccount;

    @NotNull
    @Column(nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(nullable = false)
    private Type type;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;  // createdAt = LocalDateTime.now() in a service

    @NotNull
    @Column(nullable = false)
    private String referenceId;

    public Transaction(){}

    public int getId() {
        return id;
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public Type getType(){
        return type;
    }
    public String getReferenceId(){
        return referenceId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setBankAccount(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public void setType(Type type){
        this.type = type;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public void setReferenceId(String referenceId){
        this.referenceId = referenceId;
    }
}
