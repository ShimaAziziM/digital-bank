package com.digitalbank.dto;

import com.digitalbank.entity.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
    private CustomerStatus customerStatus;



}
