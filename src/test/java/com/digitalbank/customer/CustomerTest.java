package com.digitalbank.customer;

import com.digitalbank.dto.CustomerResponseDTO;
import com.digitalbank.entity.Customer;
import com.digitalbank.entity.CustomerStatus;
import com.digitalbank.exception.CustomerAlreadyClosedException;
import com.digitalbank.mapper.CustomerMapper;
import com.digitalbank.repository.CustomerRepository;
import com.digitalbank.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private CustomerMapper customerMapper;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
        customerService = new CustomerService(customerRepository, customerMapper);
    }


    public Customer createCustomer() {
        Customer customer  = new Customer();
        customer.setFirstName("shima");
        customer.setLastName("Azizi");
        customer.setPhone("09303253489");
        customer.setUserName("shimo.mio");
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
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
        dto.setCustomerStatus(inputCustomer.getCustomerStatus());
        dto.setPhone(inputCustomer.getPhone());


        when(customerRepository.findById(1))
                .thenReturn(Optional.of(inputCustomer));



        CustomerResponseDTO returnCustomer = customerService.getCustomerById(1);
        Assertions.assertEquals(inputCustomer.getId(), returnCustomer.getId());
    }

    @Test
    public void shouldCloseCustomerSuccessfully(){
        Customer inputCustomer = createCustomer();
        inputCustomer.setId(2);
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(inputCustomer.getId());
        dto.setFirstName(inputCustomer.getFirstName());
        dto.setLastName(inputCustomer.getLastName());
        dto.setUserName(inputCustomer.getUserName());
        dto.setCustomerStatus(inputCustomer.getCustomerStatus());
        dto.setPhone(inputCustomer.getPhone());

        when(customerRepository.findById(2))
                .thenReturn(Optional.of(inputCustomer));
        when(customerRepository.save(inputCustomer))
                .thenReturn(inputCustomer);



        CustomerResponseDTO returnCustomer = customerService.closeCustomer(2);

        Assertions.assertEquals(CustomerStatus.CLOSED, returnCustomer.getCustomerStatus());

    }
    @Test
    public void shouldThrowExceptionWhenCustomerAlreadyClosed() {
        Customer inputCustomer = createCustomer();
        inputCustomer.setId(3);
        inputCustomer.setCustomerStatus(CustomerStatus.CLOSED);

        when(customerRepository.findById(3))
                .thenReturn(Optional.of(inputCustomer));

        CustomerAlreadyClosedException exception = Assertions.assertThrows(CustomerAlreadyClosedException.class, () -> customerService.closeCustomer(3));


        Assertions.assertEquals("Status is already CLOSED.", exception.getMessage());
    }
}
