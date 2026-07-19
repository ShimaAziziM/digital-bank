package com.digitalbank.service;

import com.digitalbank.dto.CustomerResponseDTO;
import com.digitalbank.entity.Customer;
import com.digitalbank.entity.CustomerStatus;
import com.digitalbank.mapper.CustomerMapper;
import com.digitalbank.repository.CustomerRepository;
import com.digitalbank.exception.*;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    final private CustomerRepository customerRepository;
    final private CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    public Customer createCustomer(Customer dto){
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setUserName(dto.getUserName());
        customer.setPhone(dto.getPhone());
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        return customerRepository.save(customer);
    }

    public CustomerResponseDTO getCustomerById(int id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not Found."));
        return customerMapper.toDTO(customer);
        //return customerRepository.findById(id)
        //        .orElseThrow(() -> new CustomerNotFoundException("Customer not Found"));
    }


    public Customer closeCustomer(int id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not Found."));
        if (customer.getCustomerStatus() != CustomerStatus.CLOSED)
            customer.setCustomerStatus(CustomerStatus.CLOSED);
        else {
            throw new CustomerAlreadyClosedException("Customer com.digitalbank.entity.Status is already CLOSED.");
        }
        return customerRepository.save(customer);

    }
    public Customer activeCustomer(int id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not Found."));
        if (customer.getCustomerStatus() != CustomerStatus.ACTIVE){
            customer.setCustomerStatus(CustomerStatus.ACTIVE);
        } else{
            throw  new CustomerAlreadyActiveException("Customer com.digitalbank.entity.Status is already ACTIVE.");
        } return customerRepository.save(customer);
    }
    public Customer updateCustomer(Customer customer) {

        Customer existCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not Found"));


            if (customer.getFirstName() != null) {
                existCustomer.setFirstName(customer.getFirstName());
            }
            if (customer.getLastName() != null) {
                existCustomer.setLastName(customer.getLastName());
            }
            if (customer.getPhone() != null) {
                existCustomer.setPhone(customer.getPhone());
            }
               // برای اینجا باید یک متد بنویسم برای این همه if

        return customerRepository.save(existCustomer);
    }
}
