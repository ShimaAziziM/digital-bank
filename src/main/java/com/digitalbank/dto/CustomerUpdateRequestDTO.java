package com.digitalbank.dto;

import com.digitalbank.entity.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
    private CustomerStatus customerStatus;
}
