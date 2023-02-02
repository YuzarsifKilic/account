package com.yuzarsif.accountdemo.service;

import com.yuzarsif.accountdemo.dto.CustomerDto;
import com.yuzarsif.accountdemo.dto.CustomerDtoConverter;
import com.yuzarsif.accountdemo.exception.CustomerNotFoundException;
import com.yuzarsif.accountdemo.model.Customer;
import com.yuzarsif.accountdemo.repository.CustomerRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);

        customerService = new CustomerService(customerRepository, customerDtoConverter);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomerDto() {
        Customer customer = new Customer("123456", "Yusuf", "Kılıç", Set.of());
        CustomerDto customerDto = new CustomerDto("123456", "Yusuf", "Kılıç", Set.of());

        Mockito.when(customerRepository.findById("123456")).thenReturn(Optional.of(customer));
        Mockito.when(customerDtoConverter.convertCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerById("123456");

        assertEquals(result, customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExists_shouldReturnCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("123456")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById("123456"));

        Mockito.verifyNoInteractions(customerDtoConverter);
    }

}