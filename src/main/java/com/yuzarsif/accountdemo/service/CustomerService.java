package com.yuzarsif.accountdemo.service;

import com.yuzarsif.accountdemo.dto.*;
import com.yuzarsif.accountdemo.exception.CustomerNotFoundException;
import com.yuzarsif.accountdemo.model.Customer;
import com.yuzarsif.accountdemo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: " + id));
    }

    public AccountCustomerDto createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(
                request.getName(),
                request.getSurname());

        return customerDtoConverter.convertToAccountCustomer(customerRepository.save(customer));
    }

    public CustomerDto getCustomerById(String customerId) {
        return customerDtoConverter.convertCustomerDto(findCustomerById(customerId));


    }
}
