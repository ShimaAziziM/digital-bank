package com.digitalbank.customer;

import com.digitalbank.dto.CustomerResponseDTO;
import com.digitalbank.entity.Customer;
import com.digitalbank.mapper.CustomerMapper;
import com.digitalbank.repository.CustomerRepository;
import com.digitalbank.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerService customerService;


    public Customer createCustomer() {
        Customer customer  = new Customer();
        customer.setFirstName("shima");
        customer.setLastName("Azizi");
        customer.setPhone("09303253489");
        customer.setUserName("shimo.mio");
         return customer;
    }

    @Test
    public void shouldGetCustomerById(){
        Customer inputCustomer = createCustomer();
        inputCustomer.setId(1);

        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(inputCustomer.getId());
        dto.setFirstName(inputCustomer.getFirstName());
        dto.setLastName(inputCustomer.getLastName());
        dto.setUserName(inputCustomer.getUserName());
        dto.setPhone(inputCustomer.getPhone());


        when(customerRepository.findById(1))
                .thenReturn(Optional.of(inputCustomer));

        when(customerMapper.toDTO(inputCustomer))
                .thenReturn(dto);

        CustomerResponseDTO returnCustomer = customerService.getCustomerById(1);
        Assertions.assertEquals(inputCustomer.getId(), returnCustomer.getId());


    }
}
