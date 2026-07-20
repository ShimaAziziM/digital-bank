package com.digitalbank.mapper;

import com.digitalbank.dto.CustomerCreateRequestDTO;
import com.digitalbank.dto.CustomerResponseDTO;
import com.digitalbank.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerMapper(){
    }
    public Customer toEntity(CustomerCreateRequestDTO dto){
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setUserName(dto.getUserName());
        customer.setPhone(dto.getPhone());
        return customer;
    }

    public  CustomerResponseDTO toDTO(Customer customer){
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setUserName(customer.getUserName());
        dto.setPhone(customer.getPhone());
        dto.setCustomerStatus(customer.getCustomerStatus());
        return dto;
    }
}

//entity -> dto
//dto -> entity