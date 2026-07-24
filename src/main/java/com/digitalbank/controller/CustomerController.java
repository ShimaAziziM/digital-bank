package com.digitalbank.controller;

import com.digitalbank.dto.CustomerResponseDTO;
import com.digitalbank.entity.Customer;
import com.digitalbank.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return (Customer) customerService.createCustomer(customer);
    }
    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }
    @PostMapping("/close/{id}")
    public CustomerResponseDTO closeCustomer(@PathVariable int id){
        return customerService.closeCustomer(id);
    }
    @PostMapping("/active/{id}")
    public Customer activeCustomer(@PathVariable int id){
        return customerService.activeCustomer(id);
    }
    @PostMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer){
       return customerService.updateCustomer(customer);
    }
}
