package com.digitalbank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(length = 15, nullable = false)
    private String firstName;

    @NotBlank
    @Column(length= 15, nullable = false)
    private String lastName;

    @NotBlank
    @Column(length = 15, nullable = false)
    private String userName;

    @NotBlank
    @Size(min = 10, max = 15)
    @Column(nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerStatus customerStatus;

    public Customer(){}

    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUserName(){
        return userName;
    }
    public String getPhone(){
        return phone;
    }
    public CustomerStatus getCustomerStatus(){
        return customerStatus;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setCustomerStatus(CustomerStatus customerStatus){
        this.customerStatus = customerStatus;
    }
}

